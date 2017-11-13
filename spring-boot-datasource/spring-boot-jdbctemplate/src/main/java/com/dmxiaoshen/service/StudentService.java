package com.dmxiaoshen.service;

import com.dmxiaoshen.entity.Student;

import java.util.List;

/**
 * Created by hzhsg on 2017/11/10.
 */
public interface StudentService {

    int addStudent(Student student);

    int updateStudent(Student student);

    Student getStudentById(String id);

    Student getStudentSimpleById(String id);

    int deleteStudent(String id);

    List<Student> getStudentList();
}
