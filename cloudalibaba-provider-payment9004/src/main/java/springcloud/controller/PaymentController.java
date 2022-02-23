package springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhy
 * @create 2022-02-21 5:35 PM
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap= new HashMap<>(16);
    static {
        hashMap.put(1L,new Payment(1L,"sdiquiuui1uibubuibeiu1"));
        hashMap.put(2L,new Payment(2L,"abdinbbkbkdjqbkjqwjked"));
        hashMap.put(3L,new Payment(3L,"vjfnvjfnvenenvurenuren"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult result = new CommonResult(200, "from mysql,serverPort:" + serverPort, payment);
        return result;
    }

}
