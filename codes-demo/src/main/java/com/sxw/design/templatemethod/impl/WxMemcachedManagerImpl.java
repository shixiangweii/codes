package com.sxw.design.templatemethod.impl;

import com.sxw.design.templatemethod.AbstractMemcachedManager;

import javax.annotation.PostConstruct;

/**
 * @author shixiangweii
 * @date 2019/8/10 15:01
 */
public class WxMemcachedManagerImpl extends AbstractMemcachedManager {

    /**
     * 结合spring上下文，使用这个注解实现自动调用
     */
    @PostConstruct
    @Override
    protected void init() {
        // 用户名+密码的认证方式
    }
}
