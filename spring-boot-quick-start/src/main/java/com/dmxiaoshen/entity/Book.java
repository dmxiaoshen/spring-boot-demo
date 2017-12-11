package com.dmxiaoshen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hzhsg on 2017/12/11.
 */
public class Book implements Serializable{
    private static final long serialVersionUID = -6643295715915218727L;

    @JsonProperty
    private String FeeId;
    private BigDecimal interest;
    private Integer number;
    @JsonProperty("money")
    private BigDecimal fqMoney;
    private BigDecimal yhMoney;
    private BigDecimal sxFee;

    @JsonIgnore
    public String getFeeId() {
        return FeeId;
    }

    public void setFeeId(String feeId) {
        FeeId = feeId;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getFqMoney() {
        return fqMoney;
    }

    public void setFqMoney(BigDecimal fqMoney) {
        this.fqMoney = fqMoney;
    }

    public BigDecimal getYhMoney() {
        return yhMoney;
    }

    public void setYhMoney(BigDecimal yhMoney) {
        this.yhMoney = yhMoney;
    }

    public BigDecimal getSxFee() {
        return sxFee;
    }

    public void setSxFee(BigDecimal sxFee) {
        this.sxFee = sxFee;
    }
}
