package com.owner.demo.algorithm.dynamic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lukewei
 * @date 2021/4/30 17:14
 */

public class Shop implements Serializable {

    private static final long serialVersionUID = 4013622476931179457L;

    /**
     * 店铺编号
     */
    private String shopCode;

    /**
     * 店铺优惠券列表
     */
    private List<ShopCoupon> shopCoupons = new ArrayList<>(1);

    public Shop() {
    }

    public Shop(String shopCode, List<ShopCoupon> shopCoupons) {
        this.shopCode = shopCode;
        this.shopCoupons = shopCoupons;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public List<ShopCoupon> getShopCoupons() {
        return shopCoupons;
    }

    public void setShopCoupons(List<ShopCoupon> shopCoupons) {
        this.shopCoupons = shopCoupons;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopCode='" + shopCode + '\'' +
                ", shopCoupons=" + shopCoupons +
                '}';
    }
}
