package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhy
 * @create 2022-02-21 12:49 PM
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        /*try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info(Thread.currentThread().getName()+"\t" +"...testA");
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){

        return "-----testB";
    }

    @GetMapping("/testD")
    public String testD() throws InterruptedException {
        /*TimeUnit.SECONDS.sleep(1);
        log.info("testD 测试RT");*/
        log.info("testD 异常比例");
        int age = 10/0;
        return  "-----testD";
    }

    @GetMapping("/testE")
    public String testE() throws InterruptedException {
        /*TimeUnit.SECONDS.sleep(1);
        log.info("testD 测试RT");*/
        log.info("testE 异常数");
        int age = 10/0;
        return  "-----testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,@RequestParam(value = "p2",required = false)String p2){
        return "---------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException e){
        return ":)))))) deal_testHotKey";
    }
}
