package com.course.generator.util;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FreeMarkerUtil {
    //模板路径（通用）
    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";
    static Template temp;//声明模板

    /*
     * 读取模板
     * */
    public static void initConfig(String ftlName) throws IOException {
        //指定freemarker的版本
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        //读取模板路径
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));

        //添加到模板
        temp = cfg.getTemplate(ftlName);//ftlName是模板名称
    }


    /*
     * 将读取的文件写到模板中
     * fileName是全路径的文件名
     * */
    public static void generator(String fileName, Map<String,Object> map) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);//放到缓存中
        temp.process(map,bw);
        bw.flush();//清除缓存
        fw.close();
    }
}
