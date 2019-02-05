package com.sxw.dubbodemo.quickstart.provider.service;

import com.sxw.dubbodemo.quickstart.api.inter.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}