package com.lyz.sharding.mapper;

import com.lyz.sharding.entity.Student;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:35
 *
 * @author shixiangweii
 */
public interface StudentMapper {
    Integer insert(Student s);

    List<Student> findAll();

    List<Student> findByStudentIds(List<Integer> studentIds);

    Student getStudentById(int studentId);

    List<Student> getStudentsById(int studentId);


}
