package com.owner.demo.algorithm.dynamic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lukewei
 * @date 2021/4/30 17:22
 */
public class PlatformCoupon implements Serializable {

    private static final long serialVersionUID = -5315932471661368167L;

    private String couponCode;

    private Long amount;

    private List<String> used = new ArrayList<>(1);

    public PlatformCoupon() {
    }

    public PlatformCoupon(String couponCode, Long amount, List<String> used) {
        this.couponCode = couponCode;
        this.amount = amount;
        this.used = used;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<String> getUsed() {
        return used;
    }

    public void setUsed(List<String> used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "PlatformCoupon{" +
                "couponCode='" + couponCode + '\'' +
                ", amount=" + amount +
                ", used=" + used +
                '}';
    }
}
