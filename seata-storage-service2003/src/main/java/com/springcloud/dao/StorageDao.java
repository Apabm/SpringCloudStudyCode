package com.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhy
 * @create 2022-02-23 00:32
 */
@Mapper
public interface StorageDao {
    void decrease(@Param("productId")Long productId,@Param("count")Integer count);
}
