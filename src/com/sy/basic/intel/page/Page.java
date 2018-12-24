/**
 * 
 */
package com.sy.basic.intel.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author dietrich
 * @date 2013-10-21 上午12:05:58
 */
@SuppressWarnings("rawtypes")
public class Page implements Serializable {
    /**
     *  
     */
    private static final long serialVersionUID = 5346551249888375686L;
    /** 查询数据 */
    private List data;
    /** 当前页号 */
    private int currentPageNo = 1;
    /** 总行数 */
    private int totalRows;
    /** 每页显示条数 */
    private int pageSize;

    public Page(int currentPageNo, int pageSize) {
	if (currentPageNo <= 0) {
	    currentPageNo = 1;
	}
	this.currentPageNo = currentPageNo;
	this.pageSize = pageSize;
    }

    public Page(int currentPageNo, int totalRows, int pageSize, List data) {
	if (currentPageNo <= 0) {
	    currentPageNo = 1;
	}
	this.data = data;
	this.currentPageNo = currentPageNo;
	this.totalRows = totalRows;
	this.pageSize = pageSize;
    }

    /**
     * 得到总页数
     * @return
     */
    public int getTotalPages() {
	if (totalRows == 0) {
	    return 1;
	}
	return (totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1);
    }

    /**
     * 得到第一页页号
     * @return
     */
    public int getFirstPageNo() {
	return 1;
    }

    /**
     * 得到最后一页页号
     * @return
     */
    public int getLastPageNo() {
	return getTotalPages();
    }

    /**
     * 得到上一页页号
     * @return
     */
    public int getPrePageNo() {
	if (currentPageNo == 1) {
	    return 1;
	}
	return currentPageNo - 1;
    }

    /**
     * 得到下一页页号
     * @return
     */
    public int getNextPageNo() {
	if (currentPageNo == getTotalPages()) {
	    return currentPageNo;
	}
	return currentPageNo + 1;
    }

    public List getData() {
	return data;
    }

    public void setData(List data) {
	this.data = data;
    }

    public int getCurrentPageNo() {
	return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
	this.currentPageNo = currentPageNo;
    }

    public int getTotalRows() {
	return totalRows;
    }

    public void setTotalRows(int totalRows) {
	this.totalRows = totalRows;
    }

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * 得到起始索引位置
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
	if (0 > pageNo) {
	    // throw new IllegalArgumentException("页面索引不能小于0!");
	    pageNo = 1;
	}
	return (pageNo - 1) * pageSize;
    }

    /**
     * 计算该页起始下标
     * @return
     */
    public int computerFirstRowIndex() {
	return (currentPageNo - 1) * pageSize;
    }

    /**
     * 克隆新的分页对象
     * @return
     */
    public Page clonePage() {
	try {
	    return (Page) super.clone();
	} catch (CloneNotSupportedException e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean getIsHasNext() {
	return currentPageNo < this.getTotalPages();
    }

    /**
     * 是否有上一页
     * @return
     */
    public boolean getIsHasPre() {
	return currentPageNo > 1;
    }
}
