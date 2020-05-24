package com.course.server.utils;

import java.util.UUID;

public class UuidUtil {
    public static String[] chars =  new String[]{
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
            "q", "r","s","t","u","v","w","x","y","z","0","1","2","3","4","5",
            "6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M",
            "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };

    /*
    * 获取短id
    * */
    public static String getShortId(){
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UuidUtil.getUuid();
        for (int i = 0 ;i<8;i++){
            //开始剪切生成的32位字符串
            String str = uuid.substring(i*4,i*4+4);
            //将剪切的字符串转为16进制
            int x = Integer.parseInt(str,16);
            //对62取余数
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /*
    * 获取正常的uuid
    * 正常32位的uuid不能插入数据库，因为我们定义的是8位的
    * */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”
        return uuid.replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println(getShortId());
    }
}
