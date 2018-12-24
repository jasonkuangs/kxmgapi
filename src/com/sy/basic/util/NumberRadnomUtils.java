package com.sy.basic.util;

public class NumberRadnomUtils {
	/**
	 * @author yhl
	 * @date 2015年9月9日 下午6:26:26
	 * @param @param min
	 * @param @param max
	 * @param @param n
	 * @param @return
	 * @描述 产生随机数不重复
	 */
	public static int[] randomCommon(int min, int max, int n) {
		min = min - 1;
		max = max + 1;
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}
	
	/**
	 * 产生指定位数的随机数
	 * @param length 随机数位数
	 * @return
	 */
	public static String randomNum(int length){
		String vcode = "";
        for (int i = 0; i < length; i++) {
            vcode = vcode + (int)(Math.random() * 10);
        }
		return vcode;
	}

	public static void main(String[] args) {
		/*for (int i = 0; i < 1000; i++) {
			int[] array = randomCommon(1, 5, 2);
			for (int a = 0; a < array.length; a++) {
				System.out.print(array[a] + ",");
			}
		}*/
		System.out.println(randomNum(6));
	}
}
