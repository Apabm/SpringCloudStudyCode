package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhy
 * @create 2022-02-12 5:57 PM
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    /**
     * 开启负载均衡模式，不然无法识别需要哪个主机提供服务
     * @LoadBalanced 赋予了RestTemplate负载均衡的能力
     */
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
