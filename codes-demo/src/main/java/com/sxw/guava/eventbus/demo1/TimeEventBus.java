package com.sxw.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;

/**
 * https://www.iteye.com/blog/nuistcc-2246394
 * <p>
 * EventBus 消息处理总线
 * 其实感觉完全可以用spring自带的 ApplicationEvent
 *
 * @author shixi
 */
public class TimeEventBus {

    /**
     * 时间任务总线
     */
    private final static EventBus tiemEventBus = new EventBus();

    /**
     * 触发同步事件
     *
     * @param event
     */
    public static void post(Object event) {
        tiemEventBus.post(event);
    }

    /**
     * 注册事件处理器
     *
     * @param handler
     */
    public static void register(Object handler) {
        tiemEventBus.register(handler);
    }

    /**
     * 注销事件处理器
     *
     * @param handler
     */
    public static void unregister(Object handler) {
        tiemEventBus.unregister(handler);
    }
}
