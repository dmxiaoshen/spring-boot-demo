package com.dmxiaoshen.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hzhsg on 2017/12/27.
 */
public class BookCase implements Serializable{
    private List<Book> list ;

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }
}
