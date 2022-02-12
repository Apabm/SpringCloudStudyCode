package com.springcloud.service;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhy
 * @create 2022-02-12 4:48 PM
 */
public interface PaymentService {
    /**
     * 创建 payment
     * @param payment 创建的订单
     * @return int
     *
     */
    public int create(Payment payment);


    /**
     * 通过id查找payment
     * @param id 查找的id
     * @return Payment
     *
     */
    public Payment getPaymentById(@Param("id")Long id);
}
