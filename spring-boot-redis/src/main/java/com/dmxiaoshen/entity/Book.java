package com.dmxiaoshen.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hzhsg on 2017/12/4.
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -6643295715915218727L;

    private String name;
    private BigDecimal price;

    public Book(){}

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
