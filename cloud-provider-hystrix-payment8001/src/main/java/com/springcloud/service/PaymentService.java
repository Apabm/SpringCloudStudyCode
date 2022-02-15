package com.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhy
 * @create 2022-02-15 2:54 PM
 */
public interface PaymentService {
    /**
     * 模拟正常
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id);

    /**
     * 模拟异常
     * @return
     */
    public String paymentInfo_Timeout(Integer id);


    /**
     * 模拟异常，使用熔断处理
     * @return
     */
    public String paymentCircuitBreaker(@PathVariable("id")Integer id);
}
