package com.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @create 2022-02-23 15:03
 */
@Configuration
@MapperScan({"com.springcloud.dao"})
public class MybatisConfig {
}
