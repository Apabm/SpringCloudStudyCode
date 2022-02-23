package com.springcloud.service;

/**
 * @author zhy
 * @create 2022-02-23 00:47
 */
public interface StorageService {
    void decrease(Long productId,Integer count);
}
