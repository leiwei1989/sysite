package com.fangxiaoer.common;

import org.springframework.util.StringUtils;

/**
 * Created by leiwei on 2017/4/20.
 */

public class ValidateUtil {

    public static void checkNull(String key, Object value) {
        if (StringUtils.isEmpty(value)) {
            throwException(key + " could not be null");
        }
    }

    public static void throwException(String msg) {
        throw new RuntimeException(msg);
    }
}