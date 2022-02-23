package com.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;

/**
 * @author zhy
 * @create 2022-02-21 5:09 PM
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(4444,"按客户自定义,global handlerException----------1");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(4444,"按客户自定义,global handlerException--------------2");
    }
}
