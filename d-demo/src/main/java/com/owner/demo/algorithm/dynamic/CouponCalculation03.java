package com.owner.demo.algorithm.dynamic;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lizhanpeng
 * @date 2021-05-31 22:18
 */
@Slf4j
public class CouponCalculation03 {

    private static final List<Shop> shops = new ArrayList(1){{
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
    private static final List<PlatformCoupon> platformCoupons = new ArrayList(){{
        add(new PlatformCoupon("A", 109L, Arrays.asList("b")));
        add(new PlatformCoupon("D", 110L, Arrays.asList("a")));
        add(new PlatformCoupon("E", 110L, Arrays.asList("c")));
    }};

    public static void main(String[] args) {

        //计算方案，1=取各个店铺最优的；2-取各个店铺所有的券
        int computeType = 2;

        // 创建优惠券路径整合对象集合
        List<CouponCalculationEntity> couponCalculationEntities = new ArrayList<>();

        // 判断平台优惠券是否为空
        if (!platformCoupons.isEmpty()) {

            //循环平台优惠券
            for (PlatformCoupon platformCoupon : platformCoupons) {
                // 创建优惠券路径存储对象
                CouponCalculationEntity couponCalculationEntity = new CouponCalculationEntity();

                //筛选出平台券对应可用的商品编码
                List<String> used = platformCoupon.getUsed();

                //创建店铺优惠券集合
                List<ShopCoupon> shopCouponList = new ArrayList<>();

                //平台优惠券金额
                Long platformAmount = 0L;

                //店铺优惠券总金额
                Long shopCouponAllAmount = 0L;

                //循环店铺
                for (Shop shop : shops) {

                    //shopCoupon列表数量大于1表示有一个可叠加一个不可叠加
                    if (shop.getShopCoupons().size() > 1) {

                        if(computeType == 1) {
                            //过滤出最优的可叠加店铺优惠券
                            List<ShopCoupon> superpositionCoupon = shop.getShopCoupons().stream().filter(shopCoupon -> shopCoupon.getFlag()).collect(Collectors.toList());

                            //获取这张可叠加的店铺优惠券适用的商品编码
                            List<String> superpositionCouponProductCodes = Arrays.asList(superpositionCoupon.get(0).getProductCode().split(","));

                            //获取平台优惠券的商品编码和店铺优惠券的商品编码之间的交集
                            List<String> intersectionProductCodes = used.stream().filter(superpositionCouponProductCodes::contains).collect(Collectors.toList());

                            //判断平台优惠券对应的商品编码与店铺优惠券的商品编码是否有交集
                            if (!intersectionProductCodes.isEmpty()) {
                                shopCouponAllAmount = shopCouponAllAmount + superpositionCoupon.get(0).getAmount();
                                platformAmount = platformCoupon.getAmount();
                                shopCouponList.add(superpositionCoupon.get(0));
                                couponCalculationEntity.setPlatformCoupon(platformCoupon);
                            } else {
                                //不可叠加的店铺优惠券
                                List<ShopCoupon> notSuperpositionCoupon = shop.getShopCoupons().stream().filter(shopCoupon -> !shopCoupon.getFlag()).collect(Collectors.toList());
                                shopCouponAllAmount = shopCouponAllAmount + notSuperpositionCoupon.get(0).getAmount();
                                shopCouponList.add(notSuperpositionCoupon.get(0));
                            }
                        }else if (computeType == 2) {

                            for (ShopCoupon shopCoupon : shop.getShopCoupons()) {

                                shopCouponAllAmount = 0L;

                                if (shopCoupon.getFlag()) {
                                    //获取店铺优惠券适用的商品编码
                                    List<String> superpositionCouponProductCodes = Arrays.asList(shopCoupon.getProductCode().split(","));

                                    //获取平台优惠券的商品编码和店铺优惠券的商品编码之间的交集
                                    List<String> intersectionProductCodes = used.stream().filter(superpositionCouponProductCodes::contains).collect(Collectors.toList());

                                    if (!CollectionUtils.isEmpty(intersectionProductCodes)) {
                                        shopCouponAllAmount = shopCouponAllAmount + shopCoupon.getAmount();
                                        platformAmount = platformCoupon.getAmount();
                                        shopCouponList.add(shopCoupon);
                                        couponCalculationEntity.setPlatformCoupon(platformCoupon);
                                    }

                                }else{

                                    //不可叠加的店铺优惠券
//                                    List<ShopCoupon> notSuperpositionCoupon = shop.getShopCoupons().stream().filter(shopCoupon1 -> !shopCoupon1.getFlag()).collect(Collectors.toList());
                                    shopCouponAllAmount = shopCouponAllAmount + shopCoupon.getAmount();
                                    shopCouponList.add(shopCoupon);

                                }

                                couponCalculationEntity.setShopCouponAmount(shopCouponAllAmount);
                                couponCalculationEntity.setPlatformCouponAmount(platformAmount);
                                couponCalculationEntity.setAllAmount(shopCouponAllAmount+platformAmount);
                                couponCalculationEntity.setShopCouponList(shopCouponList);
                                couponCalculationEntities.add(couponCalculationEntity);

                            }
                            continue;
                        }

                    }else{
                        //获取这张优惠券信息
                        ShopCoupon shopCoupon = shop.getShopCoupons().get(0);
                        //不可叠加的优惠券包含的商品编码列表
                        List<String> shopCouponProductCodes = Arrays.asList(shopCoupon.getProductCode().split(","));
                        //获取这张不可叠加的店铺优惠券对应的商品编码列表与平台券包含的商品编码列表是否有交集
                        List<String> intersectionProductCodes = used.stream().filter(shopCouponProductCodes::contains).collect(Collectors.toList());
                        //判断这张优惠券是否可叠加
                        if (shopCoupon.getFlag()) {
                            shopCouponAllAmount = shopCouponAllAmount+shopCoupon.getAmount();
                            shopCouponList.add(shopCoupon);
                            if (!intersectionProductCodes.isEmpty()) {
                                couponCalculationEntity.setPlatformCoupon(platformCoupon);
                                platformAmount = platformCoupon.getAmount();
                            }
                        }else{

                            if (intersectionProductCodes.isEmpty()) {
                                shopCouponList.add(shopCoupon);
                                shopCouponAllAmount = shopCouponAllAmount+shopCoupon.getAmount();
                            }
                        }
                    }
                }
                couponCalculationEntity.setShopCouponAmount(shopCouponAllAmount);
                couponCalculationEntity.setPlatformCouponAmount(platformAmount);
                couponCalculationEntity.setAllAmount(shopCouponAllAmount+platformAmount);
                couponCalculationEntity.setShopCouponList(shopCouponList);
                couponCalculationEntities.add(couponCalculationEntity);

                //保存平台优惠券
                CouponCalculationEntity onlyPlatformCoupon = new CouponCalculationEntity();
                onlyPlatformCoupon.setShopCouponAmount(0L);
                onlyPlatformCoupon.setPlatformCouponAmount(platformCoupon.getAmount());
                onlyPlatformCoupon.setAllAmount(platformCoupon.getAmount());
                onlyPlatformCoupon.setPlatformCoupon(platformCoupon);
                couponCalculationEntities.add(onlyPlatformCoupon);
            }
        }

        //筛选出不用平台优惠券只用店铺优惠券路径
        Long shopCouponAmount = 0L;
        List<ShopCoupon> shopCouponList = new ArrayList<>();
        for (Shop shop : shops) {
            if (shop.getShopCoupons().size()>1) {
                List<ShopCoupon> notSuperpositionCoupon = shop.getShopCoupons().stream().filter(shopCoupon -> !shopCoupon.getFlag()).collect(Collectors.toList());
                ShopCoupon shopCoupon = notSuperpositionCoupon.get(0);
                shopCouponAmount = shopCouponAmount+shopCoupon.getAmount();
                shopCouponList.add(shopCoupon);
            }else{
                ShopCoupon shopCoupon = shop.getShopCoupons().get(0);
                shopCouponAmount = shopCouponAmount+shopCoupon.getAmount();
                shopCouponList.add(shopCoupon);
            }
        }

        // 创建优惠券路径存储对象
        CouponCalculationEntity couponCalculationEntity = new CouponCalculationEntity();
        couponCalculationEntity.setAllAmount(shopCouponAmount);
        couponCalculationEntity.setShopCouponList(shopCouponList);
        couponCalculationEntity.setShopCouponAmount(shopCouponAmount);
        couponCalculationEntity.setPlatformCouponAmount(0L);
        couponCalculationEntities.add(couponCalculationEntity);
        System.out.println(String.format("所有的优惠券路径：%s", JSON.toJSONString(couponCalculationEntities)));;

        //过滤出最优优惠券路径
        CouponCalculationEntity optimalCouponPath = couponCalculationEntities.stream().max(Comparator.comparingLong(CouponCalculationEntity::getAllAmount)).get();
        //最优路径优惠券金额
        Long optimalCouponAmount = optimalCouponPath.getAllAmount();

        System.out.println(String.format("最优路径优惠金额：%d,店铺优惠券路径：%s",
                optimalCouponAmount, JSON.toJSONString(optimalCouponPath.getShopCouponList())));
        System.out.println(String.format("平台优惠券路径：%s", JSON.toJSONString(optimalCouponPath.getPlatformCoupon())));

    }

}
