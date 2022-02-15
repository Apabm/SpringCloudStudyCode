package com.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @create 2022-02-15 10:58 PM
 * 服务端出现问题 太过繁忙或宕机
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务器宕机！";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "服务器繁忙或宕机！";
    }
}
