package com.sxw.code.serial;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 11:50
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public class Goo implements Serializable {
    private static final long serialVersionUID = 1492713310028511460L;
    private String str;
}
