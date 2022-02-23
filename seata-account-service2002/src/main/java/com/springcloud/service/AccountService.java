package com.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author zhy
 * @create 2022-02-23 10:50
 */
public interface AccountService {
    void decrease(@RequestParam("userId")Long userId, @RequestParam("money") BigDecimal money);
}