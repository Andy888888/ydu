package com.ywq.ylib.utils;

import android.text.TextUtils;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 字符串工具
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static String nullToEmpty(String value) {
        if (value == null) {
            value = EMPTY;
        }
        return value;
    }

    public static boolean isNullOrEmpty(String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        return false;
    }
}
