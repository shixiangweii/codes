package com.sxw.design.templatemethod.impl;

import com.sxw.design.templatemethod.AbstractMemcachedManager;

import javax.annotation.PostConstruct;

/**
 * @author shixiangweii
 * @date 2019/8/10 14:59
 */
public class AppMemcachedManagerImpl extends AbstractMemcachedManager {

    @PostConstruct
    @Override
    protected void init() {
        // 无需密码的认证方式
    }
}
