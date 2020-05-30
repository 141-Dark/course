package com.course.generator.server;

import com.course.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.beans.FeatureDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ServerGenerator {
    //生成的模板地址
    static String MODULE= "business";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        //替换掉ServerGenerator中对应内容
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";//替换其中的汉字
        String module = MODULE;//模块名

        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);//将Domain键映射到对应的值
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);
        map.put("module",module);
        //生成service
        FreeMarkerUtil.initConfig("service.ftl");//读取
        FreeMarkerUtil.generator(toServicePath+Domain+"Service.java",map);//生成

        //生成controller
        FreeMarkerUtil.initConfig("controller.ftl");//读取
        FreeMarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);//生成
    }
}
