package com.sxw.code.serial;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 11:32
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public class Foo implements Serializable {
    private static final long serialVersionUID = -3402668034864750183L;
    private Integer name;
    private String desc;
    private Goo goo;
}
