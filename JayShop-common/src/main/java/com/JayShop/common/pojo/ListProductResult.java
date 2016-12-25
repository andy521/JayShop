package com.JayShop.common.pojo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
public class ListProductResult<T> {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
