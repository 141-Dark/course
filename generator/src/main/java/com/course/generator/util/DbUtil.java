package com.course.generator.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUtil {
    /*
    * 获取数据库名称内容
    * */
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/course";
            String user = "course";
            String pwd = "123";
            con = DriverManager.getConnection(url,user,pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    /*
    * 获得表的注释
    * */
    public static String getTableComment(String tableName) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs  = statement.executeQuery("select table_comment form information_schema.tables " +
                "WHERE table_name='" + tableName + "'");
        String tableNameCH = "";
        if (rs!=null){
            while (rs.next()){
                tableNameCH = rs.getString("table_comment");
                break;
            }
        }
        rs.close();
        statement.close();
        System.out.println("表名是："+tableNameCH);
        return tableNameCH;
    }

    /*
    * 获得所有列的信息
    * */
    public static List<Field> getColumnByTableName(String tableName) throws SQLException {
        List<Field> fieldList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("show full columns from " + tableName);
        if (rs!=null){
            while (rs.next()){
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String comment = rs.getString("Comment");
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(rs.getString("Type")));
                field.setComment(comment);

                if (comment.contains("|")){
                    field.setNameCn(comment.substring(0,comment.indexOf("|")));
                }else {
                    field.setNameCn(comment);
                }
                fieldList.add(field);
            }
        }
        rs.close();
        statement.close();
        connection.close();
        System.out.println("列信息："+fieldList);
        return fieldList;
    }

    /*
    * 下划线改成小驼峰
    * */
    public static String lineToHump(String str){
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /*
    * 下划线转大驼峰
    * */
    public static String lineToBigHump(String str){
        String s = lineToHump(str);
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }

    /*
    * 数据库类型转为java类型
    * */
    public static String sqlTypeToJavaType(String sqlType){
        if (sqlType.toUpperCase().contains("varchar".toUpperCase())
        ||sqlType.toUpperCase().contains("char".toUpperCase())
        ||sqlType.toUpperCase().contains("text".toUpperCase())){
            return "String";
        }
        else if (sqlType.toUpperCase().contains("datetime".toUpperCase())){
            return "Date";
        }
        else if (sqlType.toUpperCase().contains("int".toUpperCase())){
            return "Integer";
        }
        else if (sqlType.toUpperCase().contains("long".toUpperCase())){
            return "Long";
        }
        else if (sqlType.toUpperCase().contains("decimal".toUpperCase())){
            return "BigDecimal";
        }
        else {
            return "String";
        }
    }
}
