package com.sy.basic.util;

import java.util.UUID;

public class UuidUtil {

	/**
	 * 自动生成主键的方法
	 * 
	 * @return
	 */
	public static String get32UUIDss() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(get32UUIDss());
	}
}
