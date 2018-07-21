package com.sxw.code.optional;

import lombok.Data;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-21
 * Time: 11:37
 *
 * @author shixiangweii
 */
@Data
public class User {
    String name;

    static boolean isNameValid(String s) {
        return s.length() > 1;
    }
}
