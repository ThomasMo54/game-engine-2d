package com.motompro.gameengine2d.manager.task;

import java.util.concurrent.TimeUnit;

public class Task {

    protected Runnable runnable;
    protected long startTime;

    protected Task(Runnable runnable, double delay, TimeUnit timeUnit) {
        this.runnable = runnable;
        this.startTime = (long) (System.currentTimeMillis() + delay * timeUnitToMillis(timeUnit));
    }

    public boolean update() {
        if(System.currentTimeMillis() >= startTime) {
            runnable.run();
            return true;
        }
        return false;
    }

    protected long timeUnitToMillis(TimeUnit timeUnit) {
        switch(timeUnit) {
            case DAYS -> {
                return 86400000;
            }
            case HOURS -> {
                return 3600000;
            }
            case MINUTES -> {
                return 60000;
            }
            case SECONDS -> {
                return 1000;
            }
            default -> {
                return 1;
            }
        }
    }
}
