package com.dmxiaoshen.service.impl;

import com.dmxiaoshen.dao.StudentDao;
import com.dmxiaoshen.entity.Student;
import com.dmxiaoshen.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzhsg on 2017/11/10.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public Student getStudentSimpleById(String id) {
        return studentDao.getStudentSimpleById(id);
    }

    @Override
    public int deleteStudent(String id) {
        return studentDao.delete(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }
}
