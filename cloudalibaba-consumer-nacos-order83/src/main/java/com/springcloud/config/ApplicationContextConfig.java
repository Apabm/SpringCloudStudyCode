package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhy
 * @create 2022-02-20 3:43 PM
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //使用ribbon和RestTemplate必须要加
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
