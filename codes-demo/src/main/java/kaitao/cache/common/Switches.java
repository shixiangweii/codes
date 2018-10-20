package kaitao.cache.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 10:54
 *
 * @author shixiangweii
 */
@Getter
@AllArgsConstructor
public enum Switches {
    /**
     * 商品类目
     *
     */
    CATEGORY(7200);
    private int expiresInSeconds;
}
