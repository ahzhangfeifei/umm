package com.umm.wfm.intf.page;

/**
 * 分页查询基础参数
 * 从第几条开始，查询多少条数据
 * Created by liuzhaoxiang on 2015/12/3.
 */
public class QueryParam<T> {
    /**
     * 从第几条开始
     */
    private Integer start;
    /**
     * 查询条数
     */
    private Integer limit;
    /**
     * 查询条件实体
     */
    private T t;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

}
