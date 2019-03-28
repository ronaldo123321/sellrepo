package com.anytec.sell.utils;

import java.util.Random;

public class KeyUtils {

    /**
     * 生成唯一的逐渐
     * 格式：时间+ 随机数
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number  =  random.nextInt(900000) + 100000;
        String s = System.currentTimeMillis() + String.valueOf(number);
        return s;

    }
}
