package com.owner.demo.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lukewei
 * @date 2021/8/23 14:51
 */
@Slf4j
public class ShopCartCountTest {
    public static void main(String[] args) {

        for (int i = 20; i > 0; i--) {
            test(i);
        }


//        for (int i = 1; i < 20; i++) {
//            test(i);
//        }


    }



    public static void test(int buyCount){
        // 商品库存 6个
        int inventory = 6;
        // 商品最大预定数量 10个
        int maxBookCount = 10;
        // 商品最小预定数量 2个
        int minBookCount = 2;
        // 购物车现在数量 8个
//        int skuCount = 8;
        // 减少购买数量
        int skuCount = buyCount + 1;
        // 增加购买数量
//        int skuCount = buyCount - 1;


        // -号操作
        if(buyCount < skuCount){

//            if(inventory > maxBookCount){
//                if(buyCount > inventory){
//                    log.error("商品库存不足");
//                }
//            }else {
//                if(buyCount > maxBookCount){
//                    log.error("商品购买数量不能大于最大预定配置");
//                }
//            }
//
//            if(inventory > minBookCount){
//                if(buyCount > inventory){
//                    log.error("商品库存不足");
//                }
//            }else {
//                if(buyCount < minBookCount){
//                    log.error("商品购买数量不能小于最小预定数量");
//                }
//            }

            if(buyCount > maxBookCount){
                log.error("商品购买数量{}不能大于最大预定配置",buyCount);
                return;
            }

            if(buyCount < minBookCount){
                log.error("商品购买数量{}不能小于最小预定数量",buyCount);
                return;
            }
        }
        // + 号操作
        if(buyCount > skuCount){

            if(inventory > maxBookCount){
                if(buyCount > inventory){
                    log.error("商品库存不足，商品购买数量{}",buyCount);
                    return;
                }
            }else {
                if(buyCount > maxBookCount){
                    log.error("商品购买数量{}不能大于最大预定配置",buyCount);
                    return;
                }
            }

            if(inventory > minBookCount){
                if(buyCount > inventory){
                    log.error("商品库存不足，商品购买数量{}",buyCount);
                    return;
                }
            }else {
                if(buyCount < minBookCount){
                    log.error("商品购买数量{}不能小于最小预定数量",buyCount);
                    return;
                }
            }
        }


        System.out.println(buyCount);
    }
}
