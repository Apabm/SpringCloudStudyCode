package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhy
 * @create 2022-02-18 1:24 AM
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3377.class,args);
    }
}
