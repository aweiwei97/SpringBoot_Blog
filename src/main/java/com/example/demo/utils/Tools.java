package com.example.demo.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

    public static String enAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");//加密和解密提供密码功能
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);//ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量。
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encryptedBytes);
    }

}
