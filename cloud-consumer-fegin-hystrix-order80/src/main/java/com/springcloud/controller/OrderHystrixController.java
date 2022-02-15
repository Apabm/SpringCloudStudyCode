package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhy
 * @create 2022-02-15 4:04 PM
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "myTest")
public class OrderHystrixController {
    @Resource
    public PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id) {
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/test/ok/{id}")
    public String testPaymentInfoOK(@PathVariable("id")Integer id){
        paymentHystrixService.paymentInfo_OK(id);
        return "success";
    }

    /*@HystrixCommand(fallbackMethod = "testPaymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/test/timeout/{id}")
    public String testPaymentInfoTimeout(@PathVariable("id")Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(id);
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    /**
     * 服务降级 参数，返回值都要和原来的方法一样 属于定制降级方法
     * @param id
     * @return
     */
    public String testPaymentInfoTimeoutHandler(@PathVariable("id")Integer id){
        return "80客户端超时！请稍后再重试！";
    }

    public String myTest(){
        return "在80端口的方法内出现问题";
    }


}
