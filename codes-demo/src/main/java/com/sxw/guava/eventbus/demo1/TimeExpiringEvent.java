package com.sxw.guava.eventbus.demo1;

/**
 * @author shixi
 */
public class TimeExpiringEvent {

    private TimeTask timeTask;

    public TimeExpiringEvent(TimeTask lifeTimeTask) {
        this.timeTask = lifeTimeTask;
    }

    public TimeTask getLifeTimeTask() {
        return timeTask;
    }

    public void setLifeTimeTask(TimeTask lifeTimeTask) {
        this.timeTask = lifeTimeTask;
    }

    @Override
    public String toString() {
        return "TimeExpiringEvent{" +
                "timeTask=" + timeTask +
                '}';
    }
}