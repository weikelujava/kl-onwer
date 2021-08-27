package com.owner.demo.test;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by kgp on 2017/4/5.
 */
public class CMIUtil {

    private static final Logger logger = LoggerFactory.getLogger("CMIUtil");

    public static void main(String[] args) {


        String secretVal = "12345678";  // dev,并行，test环境
//        String secretVal = "02D609E09BCA06BE"; // stage
        String body = "{\"code\":0,\"msg\":\"success\",\"sysTime\":\"2021-08-13 09:43:25\",\"body\":\"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\",\"sec\":\"dGVzdDE7MTYyODgxOTAwNTQwNDQ5MjswMQ==\"}";
        // 获得请求体
        RequestBody requestBody = JSON.parseObject(body, RequestBody.class);

        String originalSec = new String(Base64.getDecoder().decode(requestBody.getSec().getBytes(StandardCharsets.UTF_8)));

        // secArray
        String[] secArray = splitSec(originalSec);

        // 获得盐
        String salt = secArray[1];

        // 获得真实密钥
        String realSecret = SecureUtil.md5(secretVal.concat(salt)).toLowerCase().substring(8, 24);

        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, realSecret.getBytes(StandardCharsets.UTF_8));
        String originalBody = aes.decryptStr(requestBody.getBody());


        logger.info("");
        logger.info("");
        logger.info("originalBody:{}", originalBody);
        logger.info("");
        logger.info("");

    }

    private static final String SEPARATOR = ";";

    /**
     * 约定格式: 固定密钥key;加密盐值;加密方式
     *
     * @param sec sec
     * @return sec[]
     */
    private static String[] splitSec(String sec) {
        String[] secArray = StringUtils.split(sec, SEPARATOR);
        if (StringUtils.isBlank(secArray[0])) {
            throw new RuntimeException("固定密钥key不能为空 => " + secArray[0]);
        }
        if (secArray[1].length() != 16) {
            throw new RuntimeException("盐值需要为16位 => " + secArray[1]);
        }
        if (!"01".equals(secArray[2])) {
            throw new RuntimeException("不支持的加密方式 => " + secArray[2]);
        }
        return secArray;
    }

    @Data
    private static class RequestBody {

        private String sec;
        private String body;

    }

}
