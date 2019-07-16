package com.example.demo.utils;

import java.util.Random;

public class UUID {

    static Random r = new Random();
    /**
     * 根据一个范围，生成一个随机的整数
     *
     * @param min
     *            最小值（包括）
     * @param max
     *            最大值（包括）
     * @return 随机数
     */
    public static int random(int min, int max) {
        //随机生成整数0到max - min + 1之间
        return r.nextInt(max - min + 1) + min;
    }


    private static final char[] _UU32 = "0123456789abcdefghijklmnopqrstuv".toCharArray();

    public static String UU32() {
        return UU32(java.util.UUID.randomUUID());
    }

    public static String UU32(java.util.UUID uu) {
        StringBuilder sb = new StringBuilder();
        long m = uu.getMostSignificantBits();
        long l = uu.getLeastSignificantBits();
        for (int i = 0; i < 13; i++) {
            sb.append(_UU32[(int) (m >> ((13 - i - 1) * 5)) & 31]);
        }
        for (int i = 0; i < 13; i++) {
            sb.append(_UU32[(int) (l >> ((13 - i - 1)) * 5) & 31]);
        }
        return sb.toString();
    }

}
