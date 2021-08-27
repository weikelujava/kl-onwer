package com.owner.demo.algorithm.dynamic;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lukewei
 * @date 2021/8/9 11:22
 */
public class DynamicRoutingAlgorithmTest {


    public static void main(String[] args) {
        
        // 优惠券个数
        int n = 3;
        // 订单金额
        int w = 600;

        // 优惠券金额
        int []value = {6,10,120};
        // 优惠券满减金额
        int []weight = {100,200,400};
        // 分成一个小表格
        int [][]dp = new int[n+1][w+1];
        AtomicInteger count = new AtomicInteger(0);
//        System.out.println(JSON.toJSONString(dp));
        for (int i = 1; i <= n; i++) {
            //袋子在每一个容积下所装的最大的钱=优惠券
            for (int cw = 1; cw <= w; cw++) {
                //表示这个优惠券可以装
                if (weight[i - 1] <= cw) {
                    dp[i][cw] = Math.max(
                            value[i - 1] + dp[i - 1][cw - weight[i - 1]],        //我装新加的优惠券
                            dp[i - 1][cw]        //我不装这个新加的优惠券
                    );
                } else {
                    //新加的这个装不下 ，那么就取前一个优惠券装值
                    dp[i][cw] = dp[i - 1][cw];
                }
                if(JSON.toJSONString(dp[i][cw]).contains("0")){
                    count.getAndIncrement();
                }
                System.out.println(JSON.toJSONString(dp[i][cw]));
            }
            
        }
        System.out.println("数量："+count);
//        System.out.println(JSON.toJSONString(dp));
        System.out.println("优惠券最大值："+dp[n][w]);
        
    }
}
