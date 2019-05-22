package com.sxw.code.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-05-23
 * Time: 5:56
 *
 * @author shixiangweii
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private Integer code;
    private Object data;

    public BusinessException(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public BusinessException(String message, Integer code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    /**
     * 返回异常携带的数据信息
     *
     * @param <T> 根据返回类型，确定
     * @return 携带的扩展数据
     */
    @SuppressWarnings("unchecked")
    public <T> T getAttachData() {
        return (T) data;
    }
}
