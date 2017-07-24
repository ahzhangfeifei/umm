package com.umm.wfm.intf.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangff on 2015/11/25.
 */
public class Pager<T> implements Serializable {

    private static final long serialVersionUID = -8244132316353427517L;

    /**
     * 总条数
     */
    private int count;
    
    /**
     * 当前分页查询的记录集
     */
    private List<T> rows;

    public Pager() {
    }

    public static <T> Pager<T> empty() {
        Pager<T> pager = new Pager<T>(0, new ArrayList<T>());
        return pager;
    }

    public Pager(int count, List<T> rows) {
        this.count = count;
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
