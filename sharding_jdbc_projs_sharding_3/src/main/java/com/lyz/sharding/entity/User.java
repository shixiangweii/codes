package com.lyz.sharding.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-20
 * Time: 18:28
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String name;

    private Integer age;
}
