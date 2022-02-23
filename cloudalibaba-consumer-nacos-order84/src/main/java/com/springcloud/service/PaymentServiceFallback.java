package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @create 2022-02-21 6:56 PM
 * feign配合sentinel在service发生错误时降级
 */
@Component
public class PaymentServiceFallback implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,---------PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
