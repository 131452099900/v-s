package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement // 注解开启spring事务管理
@MapperScan("org.example.mapper") // 注解，扫描mapper接口包路径
@SpringBootApplication  // 这是一个注解，作用是标注的类成为一个springboot入口应用类
@RestController         // 注解类为控制器类，接受web请求、响应处理结果
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class);   // 启动入口应用类App
    }
}