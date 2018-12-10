package com.sxw.code.execl.sxw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-09
 * Time: 22:28
 *
 * @author shixiangweii
 */
@Getter
@Setter
@ToString
public class RowData {
    List<ColDO> cols;
}
