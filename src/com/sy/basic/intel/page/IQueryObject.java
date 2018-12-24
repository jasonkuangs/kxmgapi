package com.sy.basic.intel.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询条件对象接口
 * @author Nico
 */
public interface IQueryObject extends Serializable {

    /**
     * 分页当前页数
     * @return
     */
    public int getPageNo();

    /**
     * 分页每页显示多少条数据
     * @return
     */
    public int getPageSize();

    /**
     * 获取查询的条件语句<br/>
     * 该方法具体由实现类的builderWhere()以及addQuery(String, Object)完成
     * @return
     */
    public String getWhere();

    /**
     * 获取查询条件语句的对应参数
     * @return
     */
    public List<Object> getParams();
}
