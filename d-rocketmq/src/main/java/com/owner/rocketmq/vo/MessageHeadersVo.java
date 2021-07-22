package com.owner.rocketmq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lukewei
 * @date 2021/7/22 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHeadersVo implements Serializable {

    private static final long serialVersionUID = 5732447911564524176L;

    /**
     * ID
     */
    private String id;

    /**
     * 时间戳
     */
    private Long timestamp;


}
