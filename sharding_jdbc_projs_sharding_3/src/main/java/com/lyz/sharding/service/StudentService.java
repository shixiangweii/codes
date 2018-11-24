package com.lyz.sharding.service;

import com.lyz.sharding.entity.Student;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:39
 *
 * @author shixiangweii
 */
public interface StudentService {
    boolean insert(Student student);

    Student getById(int studentId);

    List<Student> findAll();

    List<Student> getStusById(int studentId);
}
