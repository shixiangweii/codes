package com.lyz.sharding.service.impl;

import com.lyz.sharding.entity.Student;
import com.lyz.sharding.entity.User;
import com.lyz.sharding.mapper.StudentMapper;
import com.lyz.sharding.mapper.UserMapper;
import com.lyz.sharding.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean insert(User u) {
        return userMapper.insert(u) > 0;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findByUserIds(List<Integer> ids) {
        return userMapper.findByUserIds(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionTestSucess() {
        User u = new User();
        u.setUserId(13);
        u.setAge(25);
        u.setName("war3 1.27-1120-01");
        userMapper.insert(u);

        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe-stu-01");
        studentMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionTestFailure() throws IllegalAccessException {
        User u = new User();

        u.setUserId(14);
        u.setAge(25);
        u.setName("war3 1.27 good-03");
        userMapper.insert(u);

        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe1-02");
        studentMapper.insert(student);
        throw new IllegalAccessException();
    }

}  
