package com.sxw.guava.eventbus.demo1;

import com.google.common.eventbus.Subscribe;

import javax.annotation.PostConstruct;

/**
 * @author shixi
 */
public class TimeTaskEventListener {

    @PostConstruct
    public void init() {
        TimeEventBus.register(this);
    }

    @Subscribe
    public boolean processExpiringEvent(TimeExpiringEvent expiringEvent) {
        System.out.println(expiringEvent);

        return true;
    }
}