package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class ServerGenerator {
    //生成的模板地址
    static String MODULE= "business";
    static String toDtoPath = "server\\src\\main\\java\\com\\course\\server\\dto\\";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";
    //将这个工具与mybatis-generator文件绑定（用dom4j去解析xml文件）
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws IOException, TemplateException, SQLException, DocumentException {
        //只生成配置文件的第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader saxReader  = new SAXReader();
        //读取XML文件到document中
        Document doc = saxReader.read(file);
        //获取XML文件的根节点
        Element rootElement = doc.getRootElement();
        //获得context节点
        Element contextElement = rootElement.element("context");
        //定义一个element用于遍历
        Element tableElement;
        //读取出第一个节点
        tableElement = (Element) contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0,1).toLowerCase() + Domain.substring(1);
        System.out.println("表"+tableElement.attributeValue("tableName"));
        System.out.println("Domain:"+tableElement.attributeValue("domainObjectName"));

        //替换掉ServerGenerator中对应内容
        //String Domain = "Section";
//        String domain = "section";
//        String tableNameCn = "小节";//替换其中的汉字
        String module = MODULE;//模块名

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);

        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);//将Domain键映射到对应的值
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);
        map.put("module",module);
        map.put("fieldList",fieldList);
        map.put("typeSet",typeSet);

        //生成dto
        FreeMarkerUtil.initConfig("dto.ftl");
        FreeMarkerUtil.generator(toDtoPath+Domain+"Dto.java",map);
        //生成service
        FreeMarkerUtil.initConfig("service.ftl");//读取
        FreeMarkerUtil.generator(toServicePath+Domain+"Service.java",map);//生成

        //生成controller
        FreeMarkerUtil.initConfig("controller.ftl");//读取
        FreeMarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);//生成
    }
    /*
    * 获取所有的java类型，使用set去重
    * */
    private static Set<String> getJavaTypes(List<Field> fieldList){
        Set<String> set = new HashSet<>();
        for (int i = 0;i<fieldList.size();i++){
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
