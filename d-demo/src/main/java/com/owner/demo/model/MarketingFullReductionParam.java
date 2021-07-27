package com.owner.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizhanpeng
 * @date 2021-06-07 11:26
 */
@Data
public class MarketingFullReductionParam implements Serializable {


    /**
     * 大于等于：订单金额
     */
    private Integer orderAmount;

    /**
     * 优惠金额
     */
    private Integer amount;

}
