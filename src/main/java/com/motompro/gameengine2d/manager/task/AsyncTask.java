package com.motompro.gameengine2d.manager.task;

import java.util.concurrent.TimeUnit;

public class AsyncTask extends Task {

    protected AsyncTask(Runnable runnable, double delay, TimeUnit timeUnit) {
        super(runnable, delay, timeUnit);
    }

    @Override
    public boolean update() {
        if(System.currentTimeMillis() >= startTime) {
            new Thread(runnable).start();
            return true;
        }
        return false;
    }
}
