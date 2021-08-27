package com.owner.demo.algorithm.dynamic;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizhanpeng
 * @date 2021-05-31 22:22
 */
@Data
public class CouponCalculationEntity implements Serializable {

    private static final long serialVersionUID = -9148109802006483217L;
    /**
     * 优惠券路径拼接
     */
    private String couponCodePath;

    /**
     * 店铺券及平台券个数
     */
    private Integer couponNum;

    /**
     * 是否存在平台券
     */
    private Integer platformFlag;

    /**
     * 优惠券总金额
     */
    private Long allAmount;

    /**
     * 店铺优惠券总金额
     */
    private Long shopCouponAmount;

    /**
     * 平台优惠券总金额
     */
    private Long platformCouponAmount;

    /**
     * 店铺优惠券列表
     */
    private List<ShopCoupon> shopCouponList;

    /**
     * 平台优惠券
     */
    private PlatformCoupon platformCoupon;

}
