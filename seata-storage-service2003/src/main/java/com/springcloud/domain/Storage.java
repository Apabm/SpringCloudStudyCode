package com.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhy
 * @create 2022-02-23 00:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    /**
     * 产品id
     */
    private Long id;

    /**
     * 总库存
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已有库存
     */
    private Integer used;
    /**
     * 剩余库存
     */
    private Integer residue;
}
