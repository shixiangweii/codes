package com.lyz.sharding.service;

import com.lyz.sharding.entity.User;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:38
 *
 * @author shixiangweii
 */
public interface UserService {
    boolean insert(User u);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> ids);

    void transactionTestSucess();

    void transactionTestFailure() throws IllegalAccessException;

}
