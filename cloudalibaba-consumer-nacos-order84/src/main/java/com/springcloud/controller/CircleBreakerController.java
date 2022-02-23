package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhy
 * @create 2022-02-21 5:49 PM
 * 运行时异常，java异常归fallback管理
 */
@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    /**
     * 运行时异常，java异常归fallback管理
     * blockHandler管理控制台设置的
     */
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
    exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable("id")Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("非法参数！");
        }else if(result.getData() == null){
            throw new NullPointerException("没有对应的该记录！");
        }
        return result;
    }

    /**
     * 参数一定要有Throwable throwable
     * @param id
     * @param throwable
     * @return
     */

    public CommonResult handlerFallback(@PathVariable("id")Long id, Throwable throwable){
        Payment payment = new Payment(id, null);
        return new CommonResult(444,"兜底异常handlerFallback,exception内容"+throwable.getMessage(),payment);
    }

    /**
     * 参数一定要有BlockException throwable
     * @param id
     * @param throwable
     * @return
     */
    public CommonResult blockHandler(@PathVariable("id")Long id, BlockException throwable){
        Payment payment = new Payment(id, null);
        return new CommonResult(445,"blockHandler-sentinel,无次流水"+throwable.getMessage(),payment);
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    @SentinelResource(value = "consumerPaymentSQL",blockHandler = "feignBlock",fallback = "feginFallback")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        if(id == 1){
            int a = 10 / 0;
        }
        return paymentService.paymentSQL(id);
    }

    public CommonResult<Payment> feignBlock(@PathVariable("id")Long id,BlockException blockException){
        return new CommonResult<>(4444,"发生错误");
    }

    public CommonResult<Payment> feginFallback(@PathVariable("id")Long id,Throwable blockException){
        return new CommonResult<>(4444,"发生错误fallback");
    }
}
