package com.owner.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lizhanpeng
 * @date 2021-06-07 11:25
 */
@Data
public class MarketingContentParam {

    /**
     * 营销发布id
     */
    private Integer releaseId;

    /**
     * 发布渠道 1：无忧行APP  2：无忧行小程序  3：无忧行官网
     */
    private List<Integer> releaseChannel;

    /**
     * 营销编号
     */
    private String marketingNo;

    /**
     * 类型	1:折扣, 2:满减 3：兑换 4：赠送 5：立减
     */
    private Integer marketingType;

    /**
     * 营销方式	1:活动, 2:优惠券
     */
    private Integer marketingForm;

    /**
     * 参与范围 1:平台，2：商家
     */
    private Integer marketingScope;

    /**
     * 名称
     */
    private String name;

    /**
     * 生效时间
     */
    private Long effectTime;

    /**
     * 失效时间
     */
    private Long invalidTime;

    /**
     * 满减时最大优惠金额
     */
    private Integer amount;

    /**
     * 优惠折扣
     */
    private  Float discount;

    /**
     * 满减规则，活动的话有多条
     */
    private List<MarketingFullReductionParam> marketingFullReductionList;


}
