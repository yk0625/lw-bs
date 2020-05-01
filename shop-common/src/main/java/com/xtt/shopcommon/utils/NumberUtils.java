/**
 * xtt.com Inc.
 * Copyright (c) 2019/4/9 All Rights Reserved.
 */
package com.xtt.shopcommon.utils;

/**
 * @author ：xtt
 * @date ：Created in 2019/4/9 18:01
 * @description：数字工具类
 * @version: $
 */
public class NumberUtils
{
    private static char[] numbersNo0 = ("1235678912356789").toCharArray();

    public static final String randomNumNo0(int length)
    {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersNo0[getRandom(15)];
        }
        return new String(randBuffer);
    }

    /**
     * 获得一个随机数
     *
     * @param maxRandom 随机数的取值访问
     * @return int
     */
    public static final int getRandom(int maxRandom)
    {
        return (int) ((1 - Math.random()) * maxRandom);
    }

}
