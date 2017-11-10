package com.dmxiaoshen.dao;

import com.dmxiaoshen.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by hzhsg on 2017/11/10.
 */
@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(Student student){
        //jdbcTemplate.
        return 0;
    }
}
