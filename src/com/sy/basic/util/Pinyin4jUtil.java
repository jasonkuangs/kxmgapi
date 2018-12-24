package com.sy.basic.util;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4jUtil {
    /**
     * @author yhl
     * @date 2015年9月9日 上午9:17:53
     * @param @param chinese
     * @param @return
     * @描述 获取中文汉字拼音 默认输出
     */
    public static String getPinyin(String chinese) {
	return getPinyinZh_CN(makeStringByStringSet(chinese));
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:18:12
     * @param @param chinese
     * @param @return
     * @描述 拼音大写输出
     */
    public static String getPinyinToUpperCase(String chinese) {
	return getPinyinZh_CN(makeStringByStringSet(chinese)).toUpperCase();
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:18:32
     * @param @param chinese
     * @param @return
     * @描述 拼音小写输出
     */
    public static String getPinyinToLowerCase(String chinese) {
	return getPinyinZh_CN(makeStringByStringSet(chinese)).toLowerCase();
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:18:49
     * @param @param chinese
     * @param @return
     * @描述 首字母大写输出
     */
    public static String getPinyinFirstToUpperCase(String chinese) {
	return getPinyin(chinese);
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:19:02
     * @param @param chinese
     * @param @return
     * @描述 拼音简拼输出
     */
    public static String getPinyinJianPin(String chinese) {
	return getPinyinConvertJianPin(getPinyin(chinese));
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:19:15
     * @param @param chinese
     * @param @return
     * @描述 字符集转换
     */
    public static Set<String> makeStringByStringSet(String chinese) {
	char[] chars = chinese.toCharArray();
	if (chinese != null && !chinese.trim().equalsIgnoreCase("")) {
	    char[] srcChar = chinese.toCharArray();
	    String[][] temp = new String[chinese.length()][];
	    for (int i = 0; i < srcChar.length; i++) {
		char c = srcChar[i];
		// 是中文或者a-z或者A-Z转换拼音
		if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+") && !String.valueOf(c).matches("^[1-9]\\d*$")) {
		    try {
			temp[i] = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());

		    } catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		    }
		} else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
		    temp[i] = new String[] { String.valueOf(srcChar[i]) };
		} else {
		    temp[i] = new String[] { String.valueOf(srcChar[i]) };
		}
	    }
	    String[] pingyinArray = Exchange(temp);
	    Set<String> zhongWenPinYin = new HashSet<String>();
	    for (int i = 0; i < pingyinArray.length; i++) {
		zhongWenPinYin.add(pingyinArray[i]);
	    }
	    return zhongWenPinYin;
	}
	return null;
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:19:30
     * @param @return
     * @描述 默认输出格式
     */
    public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
	format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
	format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示
	return format;
    }

    public static String[] Exchange(String[][] strJaggedArray) {
	String[][] temp = DoExchange(strJaggedArray);
	return temp[0];
    }

    private static String[][] DoExchange(String[][] strJaggedArray) {
	int len = strJaggedArray.length;
	if (len >= 2) {
	    int len1 = strJaggedArray[0].length;
	    int len2 = strJaggedArray[1].length;
	    int newlen = len1 * len2;
	    String[] temp = new String[newlen];
	    int Index = 0;
	    for (int i = 0; i < len1; i++) {
		for (int j = 0; j < len2; j++) {
		    temp[Index] = capitalize(strJaggedArray[0][i]) + capitalize(strJaggedArray[1][j]);
		    Index++;
		}
	    }
	    String[][] newArray = new String[len - 1][];
	    for (int i = 2; i < len; i++) {
		newArray[i - 1] = strJaggedArray[i];
	    }
	    newArray[0] = temp;
	    return DoExchange(newArray);
	} else {
	    return strJaggedArray;
	}
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:19:59
     * @param @param s
     * @param @return
     * @描述 首字母大写
     */
    public static String capitalize(String s) {
	char ch[];
	ch = s.toCharArray();
	if (ch[0] >= 'a' && ch[0] <= 'z') {
	    ch[0] = (char) (ch[0] - 32);
	}
	String newString = new String(ch);
	return newString;
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:20:10
     * @param @param stringSet
     * @param @return
     * @描述 字符串集合转换字符串(逗号分隔)
     */
    public static String getPinyinZh_CN(Set<String> stringSet) {
	StringBuilder str = new StringBuilder();
	int i = 0;
	for (String s : stringSet) {
	    if (i == stringSet.size() - 1) {
		str.append(s);
	    } else {
		str.append(s + ",");
	    }
	    i++;
	}
	return str.toString();
    }

    /**
     * @author yhl
     * @date 2015年9月9日 上午9:20:24
     * @param @param chinese
     * @param @return
     * @描述 获取每个拼音的简称
     */
    public static String getPinyinConvertJianPin(String chinese) {
	String[] strArray = chinese.split(",");
	String strChar = "";
	for (String str : strArray) {
	    char arr[] = str.toCharArray(); // 将字符串转化成char型数组
	    for (int i = 0; i < arr.length; i++) {
		if (arr[i] >= 65 && arr[i] < 91) { // 判断是否是大写字母
		    strChar += new String(arr[i] + "");
		}
	    }
	    strChar += ",";
	}
	return strChar;
    }

    public static void main(String[] args) {
	/*String str = "重价收买";
	System.out.println("小写输出：" + getPinyinToLowerCase(str));
	System.out.println("大写输出：" + getPinyinToUpperCase(str));
	System.out.println("首字母大写输出：" + getPinyinFirstToUpperCase(str));
	System.out.println("简拼输出：" + getPinyinJianPin(str));
     */
    }
}