package com.course.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.course.server.mapper") //添加mybatis的扫描
@ComponentScan("com.course")//扫描这个包下的所有类，将扫描到的类分配到spring容器中，
// 带有@Component、@Controller、@RestController、@Service的类会被扫描到
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
