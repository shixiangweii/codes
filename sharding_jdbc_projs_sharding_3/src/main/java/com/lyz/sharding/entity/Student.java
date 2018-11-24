package com.lyz.sharding.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:34
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = 8920597824668331209L;

    private Integer id;

    private Integer studentId;

    private String name;

    private Integer age;

    private User user;

    private Integer userId;
}
