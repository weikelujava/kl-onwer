package com.owner.demo.algorithm.dynamic;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lizhanpeng
 * @date 2021-05-31 22:18
 */
@Slf4j
public class CouponCalculation {

    private static final List<Shop> SHOP_COUPONS = new ArrayList(){{
        //flag=true:可叠加， false：不可叠加
        add(new Shop("TF", new ArrayList<ShopCoupon>(){{
            add(new ShopCoupon("3", 50L, false,"TF", "a"));
            add(new ShopCoupon("4", 1L, true,"TF", "b,a"));
            add(new ShopCoupon("5", 9L, true,"TF", "b"));
        }}));
//        add(new Shop("GL",  new ArrayList<ShopCoupon>(){{
//            add(new ShopCoupon("9", 100L, false,"GL", "c"));
//        }}));
//        add(new Shop("SS", new ArrayList<ShopCoupon>(){{
//            add(new ShopCoupon("12", 100L, false,"SS", "d, e"));
//            add(new ShopCoupon("15", 50L, true,"SS", "e"));
//        }}));

    }};
    private static final List<PlatformCoupon> PLATFORM_COUPONS = new ArrayList(){{
        add(new PlatformCoupon("A", 109L, Arrays.asList("b")));
        add(new PlatformCoupon("D", 110L, Arrays.asList("a")));
        add(new PlatformCoupon("E", 110L, Arrays.asList("c")));
    }};

    public static void main(String[] args) {

        // 商品总价格
        long productTotalPrice = 200L;


        // 平台优惠券
        PlatformCoupon[] platformCouponArray = new PlatformCoupon[PLATFORM_COUPONS.size()];
        PlatformCoupon[] platformCoupons = PLATFORM_COUPONS.toArray(platformCouponArray);
        // 店铺优惠券


        Map<String, List<ShopCoupon>> shopCouponMap = SHOP_COUPONS.stream().collect(Collectors.toMap(Shop::getShopCode, Shop::getShopCoupons));


        for (Map.Entry<String, List<ShopCoupon>> map : shopCouponMap.entrySet()) {
            String key = map.getKey();
            List<ShopCoupon> value = map.getValue();
            System.out.println(JSON.toJSONString(value));
            ShopCoupon[] shopCouponArray = new ShopCoupon[value.size()];
            ShopCoupon[] shopCoupons = value.toArray(shopCouponArray);


        }

        System.out.println(JSON.toJSONString(platformCoupons));
//        System.out.println(JSON.toJSONString(shopCoupons));


    }

}
