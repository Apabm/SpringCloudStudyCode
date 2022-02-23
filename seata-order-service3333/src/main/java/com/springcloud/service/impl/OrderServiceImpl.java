package com.springcloud.service.impl;

import com.springcloud.dao.OrderDao;
import com.springcloud.domain.Order;
import com.springcloud.service.AccountService;
import com.springcloud.service.OrderService;
import com.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhy
 * @create 2022-02-23 16:03
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = {Exception.class})
    public void create(Order order) {

        log.info("----->开始新建订单");
        orderDao.create(order);

        log.info("----->订单微服务开始调用库存，做扣减end");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用库存，做扣减End");

        //4 修改订单状态，从0到1，1代表完成
        log.info("----->修改订单状态开始");
        log.info("----->修改订单状态结束");
        orderDao.update(101L,0);
        log.info("----->下订单结束了：）：）：）");

    }
}
