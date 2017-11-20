package com.dmxiaoshen.dao;

import com.dmxiaoshen.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hzhsg on 2017/11/20.
 */
@Mapper
public interface StudentDao {

    @Select("select * from student where name=#{name}")
    Student findByName(@Param("name")String name);
}
