package com.lyz.sharding.mapper;

import com.lyz.sharding.entity.User;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:35
 *
 * @author shixiangweii
 */
public interface UserMapper {
    Integer insert(User u);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> userIds);
}
