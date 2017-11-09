package com.dmxiaoshen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 这里前缀是randomProperties，一切都行，但千万别用random
 * Created by hzhsg on 2017/11/9.
 */
@Component
@ConfigurationProperties(prefix = "randomProperties")
public class RandomProperties {

    private String label;

    private int number;

    private long bigNumber;

    private int number1;

    private int number2;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "RandomProperties{" +
                "label='" + label + '\'' +
                ", number=" + number +
                ", bigNumber=" + bigNumber +
                ", number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
