package com.course.generator.util;
/*
* 用于存储每个字段的信息。过程：根据表获取所有字段信息，再将字段信息填充到Field中，得到Field列表
* 最后将Filed变量传入模板
* */
public class Field {
    public String name;//字段名，如course_id
    public String nameHump;//字段名，小驼峰，如courseId
    public String nameBigHump;//字段名，大驼峰，如CourseId
    public String nameCn;//中文名
    public String type;//字段类型。char等
    public String javaType;//java类型，String等
    public String comment;//注释

    public Field() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameHump() {
        return nameHump;
    }

    public void setNameHump(String nameHump) {
        this.nameHump = nameHump;
    }

    public String getNameBigHump() {
        return nameBigHump;
    }

    public void setNameBigHump(String nameBigHump) {
        this.nameBigHump = nameBigHump;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
