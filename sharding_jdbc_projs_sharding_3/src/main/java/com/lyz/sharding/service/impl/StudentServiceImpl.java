package com.lyz.sharding.service.impl;

import com.lyz.sharding.entity.Student;
import com.lyz.sharding.mapper.StudentMapper;
import com.lyz.sharding.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0;
    }

}  
