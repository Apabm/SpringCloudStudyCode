package com.springcloud.service;

import com.springcloud.dao.StorageDao;
import com.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author zhy
 * @create 2022-02-23 00:46
 */
@Service
public class StorageServiceImpl implements StorageService {

    public static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        /*try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       int i = 1/0;
        LOGGER.info("------------>storage-service中扣减库存开始");
        storageDao.decrease(productId, count);
        LOGGER.info("------------>storage-service中扣减库存结束");
    }
}
