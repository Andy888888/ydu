package com.ywq.ylib.utils;

/**
 * @author yanwenqiang
 * @Date 16/7/12
 * @description 类型转换
 */
public class Convert {

    public String toString(Object obj) {
        if (checkObj(obj)) {
            return obj.toString();
        }
        return StringUtil.EMPTY;
    }

    public static int toInt(Object obj,int defValue) {
        if (checkObj(obj)) {

        }
        return defValue;
    }

    public static Boolean toBoolean(Object obj,boolean defValue) {
        if (checkObj(obj)) {

        }
        return defValue;
    }


    private static boolean checkObj(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
    }
}
