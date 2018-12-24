package com.sy.basic.intel.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询条件对象的接口实现
 * @author Nico
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class QueryObject implements IQueryObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1870723455518408738L;
    private int pageNo = 1;
    private int pageSize = 10;
    private StringBuilder builder = new StringBuilder();
    private List params = new ArrayList();

    /**
     * 
     */
    public QueryObject() {}

    /**
     * 根据总行数设置页数
     * @param totalRows
     */
    public int getPageNoByTotalRows(int totalRows) {
	return this.pageNo = totalRows % pageSize == 0 ? totalRows / pageSize : (totalRows / pageSize) + 1;
    }

    /**
     * 设置每页展示条数
     * @param pageSize
     */
    public QueryObject(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * 用于标记当前实例化对象的builderWhere()方法是否被调用过
     */
    private boolean flag = false;

    public int getPageNo() {
	return this.pageNo;
    }

    public int getPageSize() {
	return this.pageSize;
    }

    public String getWhere() {
	if (!flag) {
	    builderWhere();
	    flag = true;
	}
	return builder.toString();
    }

    public List getParams() {
	return this.params;
    }

    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * 用于子类封装查询条件
     */
    protected abstract void builderWhere();

    /**
     * 添加查询结构
     * @param where 子类调用时填写的查询条件
     * @param object 子类调用时查询条件对应的参数
     */
    protected void addQuery(String where, Object object) {
	if (where != null) {
	    builder.append(" and ");
	    builder.append(where);
	}
	if (object != null) {
	    params.add(object);
	}
    }

    protected void addQuery(String where, Object[] objects) {
	if (where != null) {
	    builder.append(where);
	}
	if (objects != null && objects.length > 0) {
	    for (Object o : objects) {
		params.add(o);
	    }
	}
    }

    protected void addQuery(String where, List objects) {
	if (where != null) {
	    builder.append(where);
	}
	if (objects != null && objects.size() > 0) {
	    for (Object o : objects) {
		params.add(o);
	    }
	}
    }
}
