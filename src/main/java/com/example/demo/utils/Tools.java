package com.example.demo.utils;

import java.util.Random;

public class Tools {

    private static final Random random = new Random();
    /**
     * 判断字符串是否为数字和有正确的值
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        //String.matches() 这个方法主要是返回是否匹配指定的字符串，如果匹配则为true,否则为false;
        //表达式 "\d" 可以匹配任意一个数字
        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }



    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

}
