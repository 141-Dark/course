//package com.course.server.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
///*
//* 跨域配置已经在gateway中配置过了，所以在这里不再需要了
//* */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**").allowedOrigins("*").allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL).allowCredentials(true).maxAge(3600);//一小时内不预检
//    }
//}
