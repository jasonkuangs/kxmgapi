package com.sy.basic.obj;

/**
 * 统一对外接口数据
 * 
 * @author xp
 * @date 2015年11月19日 上午10:08:38
 * @param <T>
 */
public class ResultJsonObject<T> {

	private Integer code;// 返回码(0：成功；非0：失败)
	private String msg;// 描述

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
