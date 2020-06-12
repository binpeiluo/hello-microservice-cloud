package com.luo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeptConsumer8080_App {
    public static void main(String[] args){
        SpringApplication.run(DeptConsumer8080_App.class,args);
    }
}
