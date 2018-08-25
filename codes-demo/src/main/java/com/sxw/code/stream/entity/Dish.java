package com.sxw.code.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-19
 * Time: 22:52
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {
        /**
         * 肉
         */
        MEAT,
        /**
         * 鱼
         */
        FISH,
        /**
         * 其他
         */
        OTHER
    }
}
