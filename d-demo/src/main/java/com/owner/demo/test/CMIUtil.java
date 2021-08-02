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


//        String secretVal = "12345678";  // dev,并行，test环境
        String secretVal = "02D609E09BCA06BE"; // stage
        String body = "{\"sec\":\"c3RhZ2VfamVnb19oNTsxNjI3ODkwOTM3NzE0MjY0OzAx\",\"body\":\"s01Jx+gynIO/n8KMeIYoM3VjFG6vxkbcP1KXwGcjgXd1NaNk6DN2hIUN09vm+UncEZ+EVVmaQ5bdEyb/6AauxRHerDTtyw4B4o5HMn5vpalqhKiWhkuwnQn3BAzj3nbXDMY9JBu0ebza6BDD9S3JwKwpU1pjPiUpfMn0vwZHAIc=\"}";
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
