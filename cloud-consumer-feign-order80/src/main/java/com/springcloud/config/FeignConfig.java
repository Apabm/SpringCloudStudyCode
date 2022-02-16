package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @create 2022-02-15 2:18 PM
 * feign的日志配置
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level logger(){
        return Logger.Level.FULL;
    }
}
