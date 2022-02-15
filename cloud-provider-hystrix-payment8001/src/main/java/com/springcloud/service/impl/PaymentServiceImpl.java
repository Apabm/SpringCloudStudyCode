package com.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;
import java.rmi.Naming;
import java.util.concurrent.TimeUnit;

/**
 * @author zhy
 * @create 2022-02-15 2:56 PM
 */
@DefaultProperties(defaultFallback = "globalFallBack")
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id + "\t" + "：）哈哈哈";
    }

    /*@HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    @HystrixCommand
    @Override
    public String paymentInfo_Timeout(Integer id) {
        int i = 10 / 0;
        /*try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_Timeout,id:" + id + "\t" + "：）哈哈哈" + "耗时3ms";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + simpleUUID;
    }

    /**
     * 定制的fallback要参数和返回值一样
     * @param id
     * @return
     */
    public String paymentCircuitBreakerHandler(@PathVariable("id") Integer id) {
        return "不能为负数：）:: id:" + id;
    }

    public String paymentInfo_Timeout_Handler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_Timeout,id:" + id + "\t" + "：! 服务超时或异常，请稍后再试";
    }

    public String globalFallBack(){
        return "这是全局的fallback！";
    }

}
