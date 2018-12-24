/**
 * 
 */
package com.sy.basic.util;

import java.math.BigDecimal;

/**
 * 转换工具
 * @author Tom
 * @date 2014-1-23 上午11:44:48
 */
public class ConversionUtil {

    /**
     * 整形正则表达式
     */
    public final static String INT_REGX = "[0-9]+";
    /**
     * 双精度正则表达式
     */
    public final static String DOUBLE_REGX = "[0-9]+([.][0-9]+){0,}";

    /**
     * 将 byte 转换成 Mb 大小
     * @param size 原长度
     * @param round 小数位
     * @return 返回四舍五入后保留 round 位的 double
     */
    public static double ByteToMbSize(double size, int round) {
	BigDecimal bigDecimal = new BigDecimal(size / (1024 * 1024));
	return bigDecimal.setScale(round, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * 将字符串转化成Integer
     * 如果str不是整形格式 则返回null
     * @param str
     * @return Integer
     */
    public static Integer StringToInteger(String str) {
	return str.matches(INT_REGX) ? Integer.valueOf(str) : null;
    }

    /**
     * 将字符串转化成 Double
     * 如果str不是双精度格式 则返回null
     * @param str
     * @return Double
     */
    public static Double StringToDouble(String str) {
	return str.matches(DOUBLE_REGX) ? Double.valueOf(str) : null;
    }
}
