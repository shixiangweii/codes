package com.lyz.sharding.service.impl;

import com.lyz.sharding.entity.Student;
import com.lyz.sharding.mapper.StudentMapper;
import com.lyz.sharding.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0;
    }

    @Override
    public Student getById(int studentId) {
        return studentMapper.getStudentById(studentId);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<Student> getStusById(int studentId) {
        return studentMapper.getStudentsById(studentId);
    }

}  
