package com.course.server.dto;

import java.util.List;

//用于解决分页问题
public class PageDto<T> {
    //当前页码
    protected int page;
    //每页的条数
    protected int size;
    //总条数
    protected long total;
    //查询到的记录
    protected List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
