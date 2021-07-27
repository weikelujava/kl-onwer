package com.owner.demo.controller;

import com.alibaba.fastjson.JSON;
import com.owner.demo.model.MarketingContentParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lukewei
 * @date 2021/7/26 11:00
 */
@Slf4j
public class CouponDemoController {

    public static void main(String[] args) {
        test();
    }

    /**
     * 优惠劵类型 折扣
     */
    private static final Integer MARKETING_TYPE_DISCOUNT_COUPON = 1;

    public static void test() {

//        String jsonObject = "[\n" +
//                "    \"{\\\"amount\\\":1000,\\\"discount\\\":1,\\\"effectTime\\\":1565913600000,\\\"invalidTime\\\":1566057599000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1000,\\\"orderAmount\\\":19900}],\\\"marketingNo\\\":\\\"121908161845371829\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满199元减10元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1592}\",\n" +
//                "    \"{\\\"amount\\\":1500,\\\"discount\\\":1,\\\"effectTime\\\":1565913600000,\\\"invalidTime\\\":1566057599000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1500,\\\"orderAmount\\\":29900}],\\\"marketingNo\\\":\\\"121908161845341828\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满299元减15元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1593}\",\n" +
//                "    \"{\\\"amount\\\":300,\\\"discount\\\":1,\\\"effectTime\\\":1565913600000,\\\"invalidTime\\\":1566057599000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":300,\\\"orderAmount\\\":5900}],\\\"marketingNo\\\":\\\"121908161845211827\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满59元减3元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1594}\",\n" +
//                "    \"{\\\"amount\\\":500,\\\"discount\\\":1,\\\"effectTime\\\":1565913600000,\\\"invalidTime\\\":1566057599000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":500,\\\"orderAmount\\\":9900}],\\\"marketingNo\\\":\\\"121908161845181826\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满99元减5元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1595}\",\n" +
//                "    \"{\\\"amount\\\":1000,\\\"discount\\\":1,\\\"effectTime\\\":1567123200000,\\\"invalidTime\\\":1567267199000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1000,\\\"orderAmount\\\":19900}],\\\"marketingNo\\\":\\\"121908300910362056\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满199元减10元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1765}\",\n" +
//                "    \"{\\\"amount\\\":1500,\\\"discount\\\":1,\\\"effectTime\\\":1567123200000,\\\"invalidTime\\\":1567267199000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1500,\\\"orderAmount\\\":29900}],\\\"marketingNo\\\":\\\"121908300910192055\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满299元减15元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1766}\",\n" +
//                "    \"{\\\"amount\\\":300,\\\"discount\\\":1,\\\"effectTime\\\":1567123200000,\\\"invalidTime\\\":1567267199000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":300,\\\"orderAmount\\\":5900}],\\\"marketingNo\\\":\\\"121908300910142054\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满59元减3元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1767}\",\n" +
//                "    \"{\\\"amount\\\":500,\\\"discount\\\":1,\\\"effectTime\\\":1567123200000,\\\"invalidTime\\\":1567267199000,\\\"marketingForm\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":500,\\\"orderAmount\\\":9900}],\\\"marketingNo\\\":\\\"121908300910072053\\\",\\\"marketingScope\\\":1,\\\"marketingType\\\":2,\\\"name\\\":\\\"满99元减5元\\\",\\\"releaseChannel\\\":[1],\\\"releaseId\\\":1768}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.8,\\\"marketingNo\\\":\\\"202010131601000190\\\",\\\"invalidTime\\\":1609430399000,\\\"notDisplay\\\":true,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,7],\\\"releaseId\\\":100134,\\\"name\\\":\\\"掌厅折扣券1013\\\",\\\"effectTime\\\":1601481600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":200,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":200,\\\"orderAmount\\\":1000}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202010131606000191\\\",\\\"invalidTime\\\":1609430399000,\\\"notDisplay\\\":true,\\\"marketingForm\\\":2,\\\"releaseId\\\":100135,\\\"name\\\":\\\"掌厅满减券1013\\\",\\\"effectTime\\\":1601481600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":4990,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":4990,\\\"orderAmount\\\":5000}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202010151139000192\\\",\\\"invalidTime\\\":1638287999000,\\\"notDisplay\\\":true,\\\"marketingForm\\\":2,\\\"releaseId\\\":100137,\\\"name\\\":\\\"掌厅满减券1015\\\",\\\"effectTime\\\":1601481600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202010151147000194\\\",\\\"invalidTime\\\":1638287999000,\\\"notDisplay\\\":true,\\\"marketingForm\\\":2,\\\"releaseId\\\":100136,\\\"name\\\":\\\"掌厅折扣券10151\\\",\\\"effectTime\\\":1601481600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":5000,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":5000,\\\"orderAmount\\\":6000}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202011111102000208\\\",\\\"invalidTime\\\":1605455999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1,9],\\\"releaseId\\\":100149,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"测试合作伙伴11.11\\\",\\\"effectTime\\\":1605024000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":3000,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":3000,\\\"orderAmount\\\":5000}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202011111115000209\\\",\\\"invalidTime\\\":1605455999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100152,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"选择合作伙伴为啥同步的是-1\\\",\\\"effectTime\\\":1605024000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202011121911000210\\\",\\\"invalidTime\\\":1609343999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1,2,3,4,5,6,7,8,9],\\\"releaseId\\\":100153,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"包车测试优惠券\\\",\\\"effectTime\\\":1605024000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.09,\\\"marketingNo\\\":\\\"202011301601000217\\\",\\\"invalidTime\\\":1609343999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,8],\\\"releaseId\\\":100172,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"TT渠道的优惠券测试\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.08,\\\"marketingNo\\\":\\\"202011301642000218\\\",\\\"invalidTime\\\":1606838399000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,3],\\\"releaseId\\\":100173,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"TT渠道的优惠券\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202011301701000219\\\",\\\"invalidTime\\\":1640966399000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1,2,3,9,8],\\\"releaseId\\\":100174,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"李易潼的优惠券\\\",\\\"effectTime\\\":1606665600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.07,\\\"marketingNo\\\":\\\"202011301730000220\\\",\\\"invalidTime\\\":1609430399000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,8],\\\"releaseId\\\":100175,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"TT的渠道侧平台7\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.07,\\\"marketingNo\\\":\\\"202011301730000220\\\",\\\"invalidTime\\\":1606924799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,3],\\\"releaseId\\\":100176,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"TT的渠道侧平台77\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.07,\\\"marketingNo\\\":\\\"202011301730000220\\\",\\\"invalidTime\\\":1606924799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100177,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"TT的渠道侧平台777\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.07,\\\"marketingNo\\\":\\\"202011301730000220\\\",\\\"invalidTime\\\":1606924799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,9],\\\"releaseId\\\":100178,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"TT的渠道侧平台73\\\",\\\"effectTime\\\":1606579200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202101061730000073\\\",\\\"invalidTime\\\":1614527999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100218,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣券0106-01\\\",\\\"effectTime\\\":1609862400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.33,\\\"marketingNo\\\":\\\"202101071449000074\\\",\\\"invalidTime\\\":1610035199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100236,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91819},{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91815}],\\\"name\\\":\\\"0107\\\",\\\"effectTime\\\":1609948800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.22,\\\"marketingNo\\\":\\\"202101071518000076\\\",\\\"invalidTime\\\":1610035199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100251,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91819},{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91815}],\\\"name\\\":\\\"折扣12\\\",\\\"effectTime\\\":1609948800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.33,\\\"marketingNo\\\":\\\"202101121401000082\\\",\\\"invalidTime\\\":1610812799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1,3],\\\"releaseId\\\":100281,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣商城\\\",\\\"effectTime\\\":1610380800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":50,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":50,\\\"orderAmount\\\":100}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202101251304000112\\\",\\\"invalidTime\\\":1611849599000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1,2,3,6,8,9],\\\"releaseId\\\":100320,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"LYT满减优惠券测试使用\\\",\\\"effectTime\\\":1611417600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":1,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202101281715000123\\\",\\\"invalidTime\\\":1611935999000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100329,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91819},{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91815},{\\\"merchantId\\\":90653,\\\"partnerId\\\":91248,\\\"storeId\\\":91111215}],\\\"name\\\":\\\"测试去使用\\\",\\\"effectTime\\\":1611763200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":1,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202103161658000156\\\",\\\"invalidTime\\\":1617206399000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100370,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91819},{\\\"merchantId\\\":90489,\\\"partnerId\\\":91147,\\\"storeId\\\":91815}],\\\"name\\\":\\\"自营和app\\\",\\\"effectTime\\\":1615824000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202105111850000068\\\",\\\"invalidTime\\\":1622476799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100495,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"券包测试1234\\\",\\\"effectTime\\\":1620662400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202105111850000068\\\",\\\"invalidTime\\\":1622476799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100496,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"券包测试7777\\\",\\\"effectTime\\\":1620662400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202105121649000748\\\",\\\"invalidTime\\\":1620835199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":106409,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣55512\\\",\\\"effectTime\\\":1620748800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202105111850000068\\\",\\\"invalidTime\\\":1622476799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100501,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"券包测试5月14\\\",\\\"effectTime\\\":1620921600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":1,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1,\\\"orderAmount\\\":100}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202105150940000071\\\",\\\"invalidTime\\\":1622476799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100502,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"5月15单张券测试\\\",\\\"effectTime\\\":1621008000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":0,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"202105151025000073\\\",\\\"invalidTime\\\":1622476799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100503,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"515折扣券满减券包\\\",\\\"effectTime\\\":1621008000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":88800,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":88800,\\\"orderAmount\\\":99900}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202105281412000080\\\",\\\"invalidTime\\\":1622822399000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":100530,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90723,\\\"partnerId\\\":91279,\\\"storeId\\\":91111250}],\\\"name\\\":\\\"531满减\\\",\\\"effectTime\\\":1622476800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":1,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1,\\\"orderAmount\\\":600}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202106071737000086\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":true,\\\"marketingForm\\\":2,\\\"releaseId\\\":100535,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"10086权益兑换券\\\",\\\"effectTime\\\":1622908800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"marketingForm\\\":2,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100121,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"name\\\":\\\"满减券0922-003(满0减0.65)\\\",\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"effectTime\\\":1600790400000,\\\"invalidTime\\\":1604159999000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"marketingForm\\\":2,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100122,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"name\\\":\\\"满减券0922-004(满0减0.65)\\\",\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"effectTime\\\":1600790400000,\\\"invalidTime\\\":1609430399000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"invalidTime\\\":1606579199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100125,\\\"name\\\":\\\"满减券0922-005(满0减0.65)\\\",\\\"effectTime\\\":1602432000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"invalidTime\\\":1603468799000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100126,\\\"name\\\":\\\"满减券0922-006(满0减0.65)\\\",\\\"effectTime\\\":1602432000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"invalidTime\\\":1603555199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100127,\\\"name\\\":\\\"满减券0922-007(满0减0.65)\\\",\\\"effectTime\\\":1602432000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":65,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":65,\\\"orderAmount\\\":0}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202009221554000184\\\",\\\"invalidTime\\\":1603123199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseChannel\\\":[0,1],\\\"releaseId\\\":100128,\\\"name\\\":\\\"满减券0922-008(满0减0.65)\\\",\\\"effectTime\\\":1602432000000}\"\n" +
//                "]";

//        String jsonObject = "[\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"CB36057vzg\\\",\\\"invalidTime\\\":1626451199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000181,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"1\\\",\\\"effectTime\\\":1626192000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":50,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":10000}],\\\"discount\\\":0.5,\\\"marketingNo\\\":\\\"CBkg82wmj5\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000204,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"满100打5折，优惠券名称最大长度测试，看看我能显示过少字符\\\",\\\"effectTime\\\":1626192000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":190,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":190,\\\"orderAmount\\\":200}],\\\"marketingNo\\\":\\\"CB3y1r5642\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000208,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"领取后未使用，停止发放\\\",\\\"effectTime\\\":1626019200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":9999,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":9999,\\\"orderAmount\\\":100000}],\\\"marketingNo\\\":\\\"CBxjv9o8jv\\\",\\\"invalidTime\\\":1627142399000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000212,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"限领3张-使用后再领\\\",\\\"effectTime\\\":1626192000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":10,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":100}],\\\"discount\\\":0.1,\\\"marketingNo\\\":\\\"CB3p16w25j\\\",\\\"invalidTime\\\":1626537599000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000214,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣券\\\",\\\"effectTime\\\":1626192000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":55,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1000}],\\\"discount\\\":0.55,\\\"marketingNo\\\":\\\"CBkmney1n5\\\",\\\"invalidTime\\\":1627142399000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90934,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000234,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"5.5折，折扣券使用后领取3张\\\",\\\"effectTime\\\":1626278400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":10,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":100}],\\\"discount\\\":0.1,\\\"marketingNo\\\":\\\"CBxv5wyo57\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000242,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"这扣11111\\\",\\\"effectTime\\\":1626019200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":999,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":999,\\\"orderAmount\\\":10000}],\\\"marketingNo\\\":\\\"CBkl8r7n8e\\\",\\\"invalidTime\\\":1627747198000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000244,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"定向券-满100减9.99\\\",\\\"effectTime\\\":1626278400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":50,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1000}],\\\"discount\\\":0.5,\\\"marketingNo\\\":\\\"CBk27j9pjd\\\",\\\"invalidTime\\\":1627747198008,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000248,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣排序验证5折\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":30,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1000}],\\\"discount\\\":0.3,\\\"marketingNo\\\":\\\"CB3z1e5mej\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000249,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"折扣排序验证3折\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":55,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1000}],\\\"discount\\\":0.55,\\\"marketingNo\\\":\\\"CBkg82wm25\\\",\\\"invalidTime\\\":1630425599000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000252,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"11定向折扣-5.5折\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":100}],\\\"discount\\\":0.01,\\\"marketingNo\\\":\\\"CBk0rmzjm2\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90924,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000259,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"一二三\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":50,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":100}],\\\"discount\\\":0.5,\\\"marketingNo\\\":\\\"CBkmney1e5\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90924,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000258,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"店铺折扣\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":25,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.25,\\\"marketingNo\\\":\\\"CBxjv9o8zz\\\",\\\"invalidTime\\\":1626451199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000136,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"2.5折\\\",\\\"effectTime\\\":1626019200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":50,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":8000}],\\\"discount\\\":0.5,\\\"marketingNo\\\":\\\"CB3q1m9zrv\\\",\\\"invalidTime\\\":1626537599000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000119,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"5折券\\\",\\\"effectTime\\\":1626019200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":70,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1000}],\\\"discount\\\":0.7,\\\"marketingNo\\\":\\\"CBk0rmzjo2\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000283,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90500,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"这是定向发放的折扣券\\\",\\\"effectTime\\\":1626451200000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":80,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.8,\\\"marketingNo\\\":\\\"CB3y1r56v1\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000308,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90946,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"店铺8折券\\\",\\\"effectTime\\\":1626278400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":70,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":0}],\\\"discount\\\":0.7,\\\"marketingNo\\\":\\\"CB36057vo5\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000309,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90946,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"店铺券7折\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":800,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":800,\\\"orderAmount\\\":1000}],\\\"marketingNo\\\":\\\"CBkmney186\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000310,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90946,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"店铺满10减8\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":280,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":280,\\\"orderAmount\\\":500}],\\\"marketingNo\\\":\\\"CBk0rmzjqq\\\",\\\"invalidTime\\\":1627660799000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90946,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000311,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90946,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"店铺券满5减2.8\\\",\\\"effectTime\\\":1626364800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":55,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":1099}],\\\"discount\\\":0.55,\\\"marketingNo\\\":\\\"CBk46nr1zp\\\",\\\"invalidTime\\\":1630425599000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90933,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000322,\\\"applyChannelList\\\":[{\\\"merchantId\\\":90933,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"满10.99打5.5折\\\",\\\"effectTime\\\":1626624000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":12000,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":12000,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CBk0rmzj5q\\\",\\\"invalidTime\\\":1630425599000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90922,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000335,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"定向发放\\\",\\\"effectTime\\\":1626710400000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":590,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":590,\\\"orderAmount\\\":1000}],\\\"marketingNo\\\":\\\"CBxjv9o877\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90934,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000336,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"定向满10减5.9\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":999900,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":999900,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CB38qv5zr4\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000337,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"123456\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":8888800,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":8888800,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CB3p16w28z\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000338,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"互斥\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1050,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1050,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CB3q1m9zpq\\\",\\\"invalidTime\\\":1626855959000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90951,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000343,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"使用后10分钟内到期\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1055,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1055,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CBkl8r7n1l\\\",\\\"invalidTime\\\":1626856859000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90951,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000344,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"使用后10分钟内到期1\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1055,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1055,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CBk5mned4l\\\",\\\"invalidTime\\\":1626857879000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90951,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000345,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"使用后10分钟内到期222\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":30,\\\"marketingType\\\":1,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":0,\\\"orderAmount\\\":10000}],\\\"discount\\\":0.3,\\\"marketingNo\\\":\\\"CBk46nr14p\\\",\\\"invalidTime\\\":1629647999000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90951,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000346,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"满100打3折-店铺\\\",\\\"effectTime\\\":1626796800000}\",\n" +
//                "    \"{\\\"marketingScope\\\":2,\\\"amount\\\":1000,\\\"marketingType\\\":5,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":1000,\\\"orderAmount\\\":0}],\\\"marketingNo\\\":\\\"CBk27j9p4v\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"supplierMerchantId\\\":90500,\\\"marketingForm\\\":2,\\\"releaseId\\\":100000348,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"定向优惠券\\\",\\\"effectTime\\\":1625673600000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":190,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":190,\\\"orderAmount\\\":200}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202107222058002473\\\",\\\"invalidTime\\\":1627747199000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":7795,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"优惠券组件测试1\\\",\\\"effectTime\\\":1626192000000}\",\n" +
//                "    \"{\\\"marketingScope\\\":1,\\\"amount\\\":2000,\\\"marketingType\\\":2,\\\"marketingFullReductionList\\\":[{\\\"amount\\\":2000,\\\"orderAmount\\\":5000}],\\\"discount\\\":1,\\\"marketingNo\\\":\\\"202107231823002474\\\",\\\"invalidTime\\\":1630425599000,\\\"notDisplay\\\":false,\\\"marketingForm\\\":2,\\\"releaseId\\\":7796,\\\"applyChannelList\\\":[{\\\"merchantId\\\":-1,\\\"partnerId\\\":-1,\\\"storeId\\\":-1}],\\\"name\\\":\\\"测试商品同步性能\\\",\\\"effectTime\\\":1626969600000}\"\n" +
//                "]";

        String jsonObject = "[{\"amount\":1000,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":1000,\"orderAmount\":0}],\"marketingNo\":\"CB6ddpvmr4\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"push弹窗\",\"releaseId\":200000081,\"supplierMerchantId\":91817},{\"amount\":1000,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":1000,\"orderAmount\":0}],\"marketingNo\":\"CBm44d16gq\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"立减券领取多张后过期可在领问题测试\",\"releaseId\":200000082,\"supplierMerchantId\":91817},{\"amount\":200,\"discount\":0.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":200,\"orderAmount\":0}],\"marketingNo\":\"CBjddn8e4g\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"下载模板测试\",\"releaseId\":200000012,\"supplierMerchantId\":91817},{\"amount\":134,\"discount\":0.0,\"effectTime\":1626796800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":134,\"orderAmount\":0}],\"marketingNo\":\"CBq77yz8o5\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"测试日志\",\"releaseId\":200000019,\"supplierMerchantId\":91817},{\"amount\":45,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":45,\"orderAmount\":0}],\"marketingNo\":\"CBm44d16zq\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"优惠券日志----\",\"releaseId\":200000058,\"supplierMerchantId\":91817},{\"amount\":1,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":1,\"orderAmount\":0}],\"marketingNo\":\"CB9pp9w8r6\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"立减券日志查看\",\"releaseId\":200000051,\"supplierMerchantId\":91817},{\"amount\":1,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":1,\"orderAmount\":0}],\"marketingNo\":\"CBrooyw2e0\",\"marketingScope\":2,\"marketingType\":5,\"name\":\"日志测试--\",\"releaseId\":200000053,\"supplierMerchantId\":91817},{\"amount\":980,\"discount\":1.0,\"effectTime\":1627228800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":980,\"orderAmount\":1000}],\"marketingNo\":\"202107261030001388\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满10减9.8-1张\",\"releaseId\":106966},{\"amount\":980,\"discount\":1.0,\"effectTime\":1627228800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":980,\"orderAmount\":1000}],\"marketingNo\":\"202107261030001388\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满10减9.8-可领多张不需使用\",\"releaseId\":106967},{\"amount\":980,\"discount\":1.0,\"effectTime\":1627228800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":980,\"orderAmount\":1000}],\"marketingNo\":\"202107261030001388\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满10减9.8-使用后领\",\"releaseId\":106968},{\"amount\":580,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":580,\"orderAmount\":600}],\"marketingNo\":\"202107191724001344\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减券4（三种均可退券）\",\"releaseId\":106904},{\"amount\":490,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":490,\"orderAmount\":500}],\"marketingNo\":\"202107191714001339\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"实物满减券1（支付前取消）\",\"releaseId\":106900},{\"amount\":380,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":380,\"orderAmount\":400}],\"marketingNo\":\"202107191720001342\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减券3（商家拒单）\",\"releaseId\":106902},{\"amount\":360,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1630425599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":360,\"orderAmount\":0}],\"marketingNo\":\"202107191712001338\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"门票、包车、实物立减券\",\"releaseId\":106898},{\"amount\":290,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":290,\"orderAmount\":0}],\"marketingNo\":\"202107191753001345\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"门票、包车、实物立减不返还\",\"releaseId\":106905},{\"amount\":280,\"discount\":1.0,\"effectTime\":1626624000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":280,\"orderAmount\":300}],\"marketingNo\":\"202107191721001343\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减券2 （支付后退单）\",\"releaseId\":106903},{\"amount\":200,\"discount\":1.0,\"effectTime\":1626883200000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":200,\"orderAmount\":0}],\"marketingNo\":\"202107221629001356\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"2限领一张\",\"releaseId\":106924},{\"amount\":200,\"discount\":1.0,\"effectTime\":1625068800000,\"invalidTime\":1630425599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":200,\"orderAmount\":500}],\"marketingNo\":\"202107231505001371\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满折5-2(相对有效期)\",\"releaseId\":106938},{\"amount\":112,\"discount\":1.0,\"effectTime\":1627228800000,\"invalidTime\":1627401599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":112,\"orderAmount\":0}],\"marketingNo\":\"202107261104001389\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"测试平台276\",\"releaseId\":106965},{\"amount\":100,\"discount\":1.0,\"effectTime\":1626969600000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":100,\"orderAmount\":0}],\"marketingNo\":\"202107221624001355\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"1组件化优惠券\",\"releaseId\":106923},{\"amount\":100,\"discount\":0.0,\"effectTime\":1625673600000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":100,\"orderAmount\":200}],\"marketingNo\":\"CB2mmlpezz\",\"marketingScope\":2,\"marketingType\":2,\"name\":\"满减优惠券\",\"releaseId\":200000072,\"supplierMerchantId\":91817},{\"amount\":90,\"discount\":0.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":90,\"orderAmount\":100}],\"marketingNo\":\"CBylln67de\",\"marketingScope\":2,\"marketingType\":2,\"name\":\"满减券日志测试\",\"releaseId\":200000056,\"supplierMerchantId\":91817},{\"amount\":88,\"discount\":1.0,\"effectTime\":1626796800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":88,\"orderAmount\":100}],\"marketingNo\":\"202107250217001386\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"使用后是否可重复领取\",\"releaseId\":106957},{\"amount\":50,\"discount\":1.0,\"effectTime\":1626796800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":50,\"orderAmount\":80}],\"marketingNo\":\"202107250016001384\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减券冻结状态查看使用时间\",\"releaseId\":106955},{\"amount\":50,\"discount\":1.0,\"effectTime\":1626710400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":50,\"orderAmount\":100}],\"marketingNo\":\"202107261019001387\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"测试优惠券-\",\"releaseId\":106958},{\"amount\":4,\"discount\":1.0,\"effectTime\":1626105600000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":4,\"orderAmount\":0}],\"marketingNo\":\"202107131832001302\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"终端app\",\"releaseId\":106857},{\"amount\":2,\"discount\":1.0,\"effectTime\":1626278400000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":2,\"orderAmount\":0}],\"marketingNo\":\"202107151058001311\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减715\",\"releaseId\":106869},{\"amount\":1,\"discount\":1.0,\"effectTime\":1622131200000,\"invalidTime\":1656604799000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":1,\"orderAmount\":0}],\"marketingNo\":\"202105281018000887\",\"marketingScope\":1,\"marketingType\":2,\"name\":\"满减自动化666\",\"releaseId\":106583},{\"amount\":0,\"discount\":0.01,\"effectTime\":1624982400000,\"invalidTime\":1627401599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202106301434001176\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"未到领取时展示暂未开始\",\"releaseId\":106786},{\"amount\":0,\"discount\":0.01,\"effectTime\":1625068800000,\"invalidTime\":1627574399000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202106301456001178\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"奥术大师多\",\"releaseId\":106788},{\"amount\":0,\"discount\":0.25,\"effectTime\":1626105600000,\"invalidTime\":1627660799000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107191711001337\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"可返还折扣券\",\"releaseId\":106897},{\"amount\":0,\"discount\":0.3,\"effectTime\":1626019200000,\"invalidTime\":1627660799000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107191714001340\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"折扣券不可返还3折\",\"releaseId\":106899},{\"amount\":0,\"discount\":0.9,\"effectTime\":1626796800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107211620001347\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"9折测试券1\",\"releaseId\":106913},{\"amount\":0,\"discount\":0.8,\"effectTime\":1626796800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107211659001351\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"8折平平台券\",\"releaseId\":106917},{\"amount\":0,\"discount\":0.01,\"effectTime\":1626364800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107241359001379\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"折扣券多次领取测试领取时间\",\"releaseId\":106946},{\"amount\":0,\"discount\":0.01,\"effectTime\":1626364800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107241359001379\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"折扣券多次领取测试领取时间--\",\"releaseId\":106947},{\"amount\":0,\"discount\":0.01,\"effectTime\":1627056000000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107241359001379\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"折扣券多次领取测试领取时间222\",\"releaseId\":106949},{\"amount\":0,\"discount\":0.12,\"effectTime\":1627228800000,\"invalidTime\":1627747199000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107261347001390\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"互斥验证\",\"releaseId\":106969},{\"amount\":0,\"discount\":0.01,\"effectTime\":1627056000000,\"invalidTime\":1629647999000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202107241939001383\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"打骨折\",\"releaseId\":106954},{\"amount\":0,\"discount\":0.01,\"effectTime\":1624550400000,\"invalidTime\":1658937599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202106251458001128\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"only短信2\",\"releaseId\":106744},{\"amount\":0,\"discount\":0.01,\"effectTime\":1624550400000,\"invalidTime\":1658937599000,\"marketingForm\":2,\"marketingFullReductionList\":[{\"amount\":0,\"orderAmount\":0}],\"marketingNo\":\"202106251551001130\",\"marketingScope\":1,\"marketingType\":1,\"name\":\"短信+push\",\"releaseId\":106746}]";
        List<MarketingContentParam> marketingContentParamList = new ArrayList<>(1);
        List<String> tmp = JSON.parseArray(jsonObject, String.class);
        for (String s : tmp) {
            marketingContentParamList.add(JSON.parseObject(s, MarketingContentParam.class));
        }

        List<MarketingContentParam> marketingContentParamList1 = marketingSort(marketingContentParamList);
        System.out.println(JSON.toJSONString(marketingContentParamList1));

//        List<MarketingContentParam> convert = filterNullAndCurrentTime(marketingContentParamList);
//        List<MarketingContentParam> list = sort(convert);
//        System.out.println(JSON.toJSONString(list));




    }

    /**
     * 对优惠劵进行排序
     *
     * @param marketingContentParams 优惠劵集合
     * @return 排序后的优惠劵集合
     * <p>
     * 注意:传入优惠劵集合中 生效时间和失效时间 为空将被过滤
     */
    public static List<MarketingContentParam> marketingSort(List<MarketingContentParam> marketingContentParams) {

        if (CollectionUtils.isEmpty(marketingContentParams)) {
            return new ArrayList<>();
        }

        marketingContentParams = filterNullAndCurrentTime(marketingContentParams);
        // 立减券集合
        List<MarketingContentParam> reductionList = new ArrayList<>();
        // 满减券集合
        List<MarketingContentParam> fullMinusList = new ArrayList<>();

        // 无门槛折扣券
        List<MarketingContentParam> noThresholdList = new ArrayList<>();
        // 有门槛折扣券
        List<MarketingContentParam> haveThresholdList = new ArrayList<>();

        // 对折扣进行比较,1:折扣, 2:满减 3：兑换 4：赠送 5：立减
        for (MarketingContentParam marketingContentParam : marketingContentParams) {

            if (marketingContentParam.getMarketingType() == null) {
                marketingContentParam.setMarketingType(-1);
            }
            if (marketingContentParam.getDiscount() == null) {
                marketingContentParam.setDiscount(0.0F);
            }
            if (marketingContentParam.getAmount() == null) {
                marketingContentParam.setAmount(0);
            }
            if (marketingContentParam.getInvalidTime() == null) {
                marketingContentParam.setInvalidTime(0L);
            }
            if (!CollectionUtils.isEmpty(marketingContentParam.getMarketingFullReductionList()) && marketingContentParam.getMarketingFullReductionList().get(0).getOrderAmount() == null) {
                marketingContentParam.getMarketingFullReductionList().get(0).setOrderAmount(0);
            }

            if (marketingContentParam.getMarketingType().equals(MARKETING_TYPE_DISCOUNT_COUPON)) {
                // 获取所有的折扣券
                if (marketingContentParam.getMarketingFullReductionList().get(0).getOrderAmount() == 0) {
                    // 获取所有的无门槛折扣券
                    noThresholdList.add(marketingContentParam);
                } else {
                    // 获取所有的有门槛折扣券
                    haveThresholdList.add(marketingContentParam);
                }
            } else {
                if (marketingContentParam.getMarketingFullReductionList().get(0).getOrderAmount() == 0) {
                    // 立减券
                    reductionList.add(marketingContentParam);
                } else {
                    // 满减券
                    fullMinusList.add(marketingContentParam);
                }
            }

        }

        // 排序，立减券
        reductionList.sort((o1, o2) -> {
            int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? -1 : 1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });

        // 满减券
        List<MarketingContentParam> sortedList = new ArrayList<>(reductionList);
        fullMinusList.sort((o1, o2) -> {
            int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? -1 : 1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });

        // 满减券
        sortedList.addAll(fullMinusList);

        // 折扣券，无门槛排序
        noThresholdList.sort((o1, o2) -> {
            int compareAmount = Float.compare(o1.getDiscount(), o2.getDiscount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? 1 : -1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });
        sortedList.addAll(noThresholdList);

        // 折扣券，有门槛券排序
        haveThresholdList.sort((o1, o2) -> {
            int compareAmount = Float.compare(o1.getDiscount(), o2.getDiscount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? 1 : -1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });
        sortedList.addAll(haveThresholdList);
        log.info("排序后的优惠券列表：{}", JSON.toJSONString(sortedList));
        return sortedList;
//
//        if (CollectionUtils.isEmpty(marketingContentParams)) {
//            return Lists.newArrayList();
//        }
//
//        // 获得当前时间
//        long currentTimeMillis = System.currentTimeMillis();
//
//        // 过滤无效的优惠劵
//        return marketingContentParams.stream().filter(item ->
//                item.getEffectTime() != null && item.getEffectTime() < currentTimeMillis && item.getInvalidTime() != null && item.getInvalidTime() > currentTimeMillis).sorted((o1, o2) -> {
//            if (o1.getMarketingType() == null) {
//                o1.setMarketingType(-1);
//            }
//            if (o2.getMarketingType() == null) {
//                o2.setMarketingType(-1);
//            }
//            if (o1.getDiscount() == null) {
//                o1.setDiscount(0.0F);
//            }
//            if (o2.getDiscount() == null) {
//                o2.setDiscount(0.0F);
//            }
//            if (o1.getAmount() == null) {
//                o1.setAmount(0);
//            }
//            if (o2.getAmount() == null) {
//                o2.setAmount(0);
//            }
//            if (o1.getInvalidTime() == null) {
//                o1.setInvalidTime(0L);
//            }
//            if (o2.getInvalidTime() == null) {
//                o2.setInvalidTime(0L);
//            }
//            // 对折扣进行比较
//            int compareMarketingType = Float.compare(o1.getMarketingType(), o2.getMarketingType());
//            if (compareMarketingType != 0) {
//                return compareMarketingType > 0 ? -4 : 4;
//            } else {
//                if (o1.getMarketingType().equals(MARKETING_TYPE_DISCOUNT_COUPON)) {
//                    // 对折扣进行比较
//                    int compareDiscount = Float.compare(o1.getDiscount(), o2.getDiscount());
//                    if (compareDiscount != 0) {
//                        return compareDiscount < 0 ? -2 : 2;
//                    }
//                } else {
//                    int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
//                    if (compareAmount != 0) {
//                        return compareAmount > 0 ? -3 : 3;
//                    }
//                }
//                int compareInvalidTime = Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
//                if (compareInvalidTime != 0) {
//                    return compareInvalidTime > 0 ? -1 : 1;
//                } else {
//                    return 0;
//                }
//            }
//        }).collect(Collectors.toList());
    }

    /**
     * 对优惠券按照 立减、满减、折扣(无门槛、有门槛)的顺序排序，如果优惠券金额相同，那再次按照优惠券的失效时间正序排序
     * @param marketingContentParams 优惠券列表
     * @return 优惠券列表
     */
    public static List<MarketingContentParam> sort(List<MarketingContentParam> marketingContentParams) {

        List<MarketingContentParam> reductionAndFullMinusList = new ArrayList<>();
        // 立减券集合
        List<MarketingContentParam> reductionList = new ArrayList<>();
        // 满减券集合
        List<MarketingContentParam> fullMinusList = new ArrayList<>();

        // 无门槛折扣券
        List<MarketingContentParam> noThresholdList = new ArrayList<>();
        // 有门槛折扣券
        List<MarketingContentParam> haveThresholdList = new ArrayList<>();

        // 对折扣进行比较,1:折扣, 2:满减 3：兑换 4：赠送 5：立减
        for (MarketingContentParam marketingContentParam : marketingContentParams) {

            if (marketingContentParam.getMarketingType() == null) {
                marketingContentParam.setMarketingType(-1);
            }
            if (marketingContentParam.getDiscount() == null) {
                marketingContentParam.setDiscount(0.0F);
            }
            if (marketingContentParam.getAmount() == null) {
                marketingContentParam.setAmount(0);
            }
            if (marketingContentParam.getInvalidTime() == null) {
                marketingContentParam.setInvalidTime(0L);
            }
            if (!CollectionUtils.isEmpty(marketingContentParam.getMarketingFullReductionList()) && marketingContentParam.getMarketingFullReductionList().get(0).getOrderAmount() == null) {
                marketingContentParam.getMarketingFullReductionList().get(0).setOrderAmount(0);
            }

            if (marketingContentParam.getMarketingType().equals(5)) {
                // 获取所有的立减券
                reductionList.add(marketingContentParam);
            } else if (marketingContentParam.getMarketingType().equals(2)) {
                // 获取所有的满减券
                fullMinusList.add(marketingContentParam);

            } else if (marketingContentParam.getMarketingType().equals(1)) {
                // 获取所有的折扣券
                if (marketingContentParam.getMarketingFullReductionList().get(0).getOrderAmount() == 0) {
                    // 获取所有的无门槛折扣券
                    noThresholdList.add(marketingContentParam);
                } else {
                    // 获取所有的有门槛折扣券
                    haveThresholdList.add(marketingContentParam);
                }
            }
        }
        // 立减券
        reductionAndFullMinusList.addAll(reductionList);
        // 满减券
        reductionAndFullMinusList.addAll(fullMinusList);

        // 排序，立减券和满减券
        reductionAndFullMinusList.sort((o1, o2) -> {
            int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? -1 : 1;
            }
            return  Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });
        List<MarketingContentParam> sortedList = new ArrayList<>(reductionAndFullMinusList);

        // 折扣券，无门槛排序
        noThresholdList.sort((o1, o2) -> {
            int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? 1 : -1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });
        sortedList.addAll(noThresholdList);

        // 折扣券，有门槛券排序
        haveThresholdList.sort((o1, o2) -> {
            int compareAmount = Long.compare(o1.getAmount(), o2.getAmount());
            if (compareAmount != 0) {
                return compareAmount > 0 ? 1 : -1;
            }
            return Long.compare(o1.getInvalidTime(), o2.getInvalidTime());
        });
        sortedList.addAll(haveThresholdList);



        return sortedList;
    }

    /**
     * 过滤优惠券列表
     * @param marketingContentParams 优惠券列表
     * @return 优惠券列表
     */
    public static List<MarketingContentParam> filterNullAndCurrentTime(List<MarketingContentParam> marketingContentParams) {
        // 获得当前时间
        long currentTimeMillis = System.currentTimeMillis();
        return marketingContentParams.stream().filter(item ->
                item.getEffectTime() != null && item.getEffectTime() < currentTimeMillis &&
                        item.getInvalidTime() != null && item.getInvalidTime() > currentTimeMillis).collect(Collectors.toList());
    }





}
