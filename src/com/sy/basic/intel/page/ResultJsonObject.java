package com.sy.basic.intel.page;

public class ResultJsonObject<T> {

    private Integer recordSize;// 总记录数
    private Integer currentPageindex;// 当前页
    private Integer currentPageSize;// 每页记录数
    private Integer resultCode;// 返回码(0：成功；非0：失败)
    private String resultDesc;// 描述
    private T searchResults;// 搜索结果列表

    public ResultJsonObject() {
	super();
    }

    public ResultJsonObject(int currentPageindex, int currentPageSize) {
	if (currentPageindex <= 0) {
	    currentPageindex = 1;
	}
	if (currentPageSize <= 0) {
	    currentPageSize = 10;
	}
	this.currentPageindex = currentPageindex;
	this.currentPageSize = currentPageSize;
    }

    public ResultJsonObject(int currentPageindex, int currentPageSize, int recordSize, int resultCode,
	    String resultDesc, T searchResults) {
	if (currentPageindex <= 0) {
	    currentPageindex = 1;
	}
	if (currentPageSize <= 0) {
	    currentPageSize = 10;
	}
	this.currentPageindex = currentPageindex;
	this.currentPageSize = currentPageSize;
	this.recordSize = recordSize;
	this.resultCode = resultCode;
	this.resultDesc = resultDesc;
	this.searchResults = searchResults;
    }

    public Integer getResultCode() {
	return resultCode;
    }

    public void setResultCode(Integer resultCode) {
	this.resultCode = resultCode;
    }

    public String getResultDesc() {
	return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
	this.resultDesc = resultDesc;
    }

    public Integer getRecordSize() {
	return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
	this.recordSize = recordSize;
    }

    public T getSearchResults() {
	return searchResults;
    }

    public void setSearchResults(T searchResults) {
	this.searchResults = searchResults;
    }

    public Integer getCurrentPageindex() {
	return currentPageindex;
    }

    public void setCurrentPageindex(Integer currentPageindex) {
	this.currentPageindex = currentPageindex;
    }

    public Integer getCurrentPageSize() {
	return currentPageSize;
    }

    public void setCurrentPageSize(Integer currentPageSize) {
	this.currentPageSize = currentPageSize;
    }

}
