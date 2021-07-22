package com.owner.rocketmq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lukewei
 * @date 2021/7/22 14:22
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo<T> {

    private static final long serialVersionUID = -2250921270762374130L;

    /**
     * 消息体
     */
    private T payload;

    /**
     * 消息头
     */
    private MessageHeadersVo headers;


}
