package com.motompro.gameengine2d.manager.task;

import java.util.concurrent.TimeUnit;

public class AsyncRepeatingTask extends RepeatingTask {

    protected AsyncRepeatingTask(Runnable runnable, double delay, double period, TimeUnit timeUnit) {
        super(runnable, delay, period, timeUnit);
    }

    @Override
    public boolean update() {
        long currentTime = System.currentTimeMillis();
        if(currentTime >= startTime && currentTime >= startTime + activations * period) {
            new Thread(runnable).start();
            activations++;
        }
        return false;
    }
}
