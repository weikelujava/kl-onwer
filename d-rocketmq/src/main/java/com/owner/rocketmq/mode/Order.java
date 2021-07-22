package com.owner.rocketmq.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sun.dc.pr.PRError;

import java.io.Serializable;

/**
 * @author lukewei
 * @date 2021/7/21 15:40
 */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -2986533999757259997L;

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单原始金额
     */
    private Long foreignPrice;

    /**
     * 实际订单金额
     */
    private Long actualAmount;

    /**
     * 下单时间，yyyy-MM-dd HH:mm:ss
     */
    private String createTimeStr;

    /**
     * 下单时间，系统时间戳
     */
    private Long  createTime;

    /**
     * 过期时间，单位：s
     */
    private int expireTime;


}
