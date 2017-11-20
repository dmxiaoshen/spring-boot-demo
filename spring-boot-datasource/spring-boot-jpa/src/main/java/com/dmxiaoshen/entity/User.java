package com.dmxiaoshen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by hzhsg on 2017/11/20.
 */
@Entity
@Table(name="t_user")
@GenericGenerator(name="jpa-uuid",strategy = "uuid")
public class User {

    @Id
    @GeneratedValue(generator = "jpa-uuid" )
    @Column(length = 32)
    private String id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Column(name="mobile_phone")
    private String mobilePhone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
