package com.sy.basic.util;

import org.springframework.util.StringUtils;

/**
 * @author xp
 * @date 2015年8月21日 上午10:18:46
 */
public class StringValidateUtils {

    /**
     * 验证字符串是否超长
     * @author xp
     * @date 2015年8月21日 上午10:12:20
     * @param str
     *            验证字符串
     * @param length
     *            长度（char单位）
     * @return
     */
    public static boolean validateLength(final String str, final Integer length) {
	char[] vStr = str.toCharArray();
	int valueLength = 0;

	for (int i = 0; i < vStr.length; i++) {
	    if (!isChinese(vStr[i])) {// 不是中文
		valueLength = valueLength + 1;
	    } else {// 是中文
		valueLength = valueLength + 3;// utf8编码占3字节
	    }
	}

	if (valueLength < length) {
	    return true;
	}

	return false;
    }

    /**
     * 验证字符串过短或过长
     * @author xp
     * @date 2015年8月26日 上午9:29:26
     * @param str
     *            验证字符串
     * @param min
     *            不小于最小长度（char单位）可以为空
     * @param max
     *            不大于最大长度（char单位）可以为空
     * @return
     */
    public static boolean validateLength(final String str, final Integer min, final Integer max) {
	boolean flag = false;
	char[] vStr = str.toCharArray();
	int valueLength = 0;

	for (int i = 0; i < vStr.length; i++) {
	    if (!isChinese(vStr[i])) {// 不是中文
		valueLength = valueLength + 1;
	    } else {// 是中文
		valueLength = valueLength + 3;// utf8编码占3字节
	    }
	}

	if (min != null) {
	    if (valueLength >= min) {
		flag = true;
	    } else {
		flag = false;
	    }
	}

	if (max != null) {
	    if (valueLength <= max) {
		flag = true;
	    } else {
		flag = false;
	    }
	}

	return flag;
    }

    /**
     * 验证字符串是否有特殊字符
     * @author xp
     * @date 2015年8月21日 上午10:12:24
     * @param str
     * @return
     */
    public static boolean validateCharacter(final String str) {
	boolean flag = false;
	if (StringUtils.hasText(str)) {
	    return flag;
	}

	// TODO 没有实现
	return flag;
    }

    /**
     * 验证是否为中文
     * @author xp
     * @date 2015年8月21日 下午3:51:12
     * @param c
     * @return
     */
    // GENERAL_PUNCTUATION 判断中文的"号
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    public static boolean isChinese(char c) {
	Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	    return true;
	}
	return false;
    }
}
