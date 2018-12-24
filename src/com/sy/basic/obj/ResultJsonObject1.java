package com.sy.basic.obj;

/**
 * 统一对外接口数据
 * 
 * @author xp
 * @date 2015年11月19日 上午10:08:38
 * @param <T>
 */
public class ResultJsonObject1<T> {
	
	private String cfgTag;
	private T cfgInfos;// 搜索结果列表

	public ResultJsonObject1() {
		super();
	}
	
	public void setCfgTag(String cfgTag) {
		this.cfgTag = cfgTag;
	}

	public String getCfgTag() {
		return cfgTag;
	}
	
	public void setCfgInfos(T cfgInfos) {
		this.cfgInfos = cfgInfos;
	}
	
	public T getCfgInfos() {
		return cfgInfos;
	}

	

}
