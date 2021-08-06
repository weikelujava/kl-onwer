package com.owner.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lukewei
 * @date 2021/8/6 14:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonQueue implements Serializable {

    private static final long serialVersionUID = -1814122161322109870L;

    private String key;

    private Object data;

}
