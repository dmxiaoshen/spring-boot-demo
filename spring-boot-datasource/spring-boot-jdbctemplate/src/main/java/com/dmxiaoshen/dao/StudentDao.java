package com.dmxiaoshen.dao;

import com.dmxiaoshen.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by hzhsg on 2017/11/10.
 */
@Repository
public class StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int insert(Student student) {
        student.setId(UUID());
        String sql = "INSERT INTO student(id,name,age,status,birthday,information,remarks,create_by,update_by,create_time,update_time,del_flag) " +
                "values(:id,:name,:age,:status,:birthday,:information,:remarks,:createBy,:updateBy,:createTime,:updateTime,:delFlag)";
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", student.getId());
        paramsMap.put("name", student.getName());
        paramsMap.put("age", student.getAge());
        paramsMap.put("status", student.getStatus());
        paramsMap.put("birthday", student.getBirthday());
        paramsMap.put("information", student.getInformation());
        paramsMap.put("remarks", student.getRemarks());
        paramsMap.put("createBy", student.getCreateBy());
        paramsMap.put("updateBy", student.getUpdateBy());
        paramsMap.put("createTime", student.getCreateTime());
        paramsMap.put("updateTime", student.getUpdateTime());
        paramsMap.put("delFlag", student.getDelFlag());

        return namedParameterJdbcTemplate.update(sql,paramsMap);
    }

    public int update(Student student){
        String sql = "update student set name=:name,age=:age,update_time=:updateTime where id=:id";
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("name",student.getName());
        paramsMap.put("age",student.getAge());
        paramsMap.put("updateTime",new Date());
        paramsMap.put("id",student.getId());

        return namedParameterJdbcTemplate.update(sql, paramsMap);
    }

    public int delete(String id){
        String sql = "update student set del_flag=1 where id=:id";
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id",id);
        return namedParameterJdbcTemplate.update(sql, paramsMap);
    }

    public Student getStudentById(String id){
        String sql = "select id,name,age,status,birthday,information,remarks,create_by,update_by,create_time,update_time,del_flag " +
                "from student where id=:id and del_flag=0";
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql,paramsMap,getStudentRowMapper());
    }

    public Student getStudentSimpleById(String id){
        String sql = "select id,name,age,status,birthday,information,remarks,create_by,update_by,create_time,update_time,del_flag " +
                "from student where id=:id and del_flag=0";
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql,paramsMap,getStudentRowMapperSimple());
    }

    public List<Student> getStudentList(){
        String sql = "select id,name,age,status,birthday,information,remarks,create_by,update_by,create_time,update_time,del_flag " +
                "from student where del_flag=0";

        return namedParameterJdbcTemplate.query(sql, getStudentRowMapper());
    }


    private String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private RowMapper<Student> getStudentRowMapper(){
        //不支持级联属性
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return rowMapper;
    }

    private RowMapper<Student> getStudentRowMapperSimple(){
        return new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setUpdateTime(resultSet.getTimestamp("update_time"));
                student.setDelFlag(resultSet.getBoolean("del_flag"));
                return student;
            }
        };
    }
}
