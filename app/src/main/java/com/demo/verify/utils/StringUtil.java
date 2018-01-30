package com.demo.verify.utils;

/**
 * @Author: ponos.peng
 * Time: 2018/1/29  15:21
 * Description:
 */

public class StringUtil {
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }
}
