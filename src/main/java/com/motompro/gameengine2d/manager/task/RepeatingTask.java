package com.motompro.gameengine2d.manager.task;

import java.util.concurrent.TimeUnit;

public class RepeatingTask extends Task {

    protected final long period;
    protected int activations = 0;

    protected RepeatingTask(Runnable runnable, double delay, double period, TimeUnit timeUnit) {
        super(runnable, delay, timeUnit);
        this.period = (long) (period * timeUnitToMillis(timeUnit));
    }

    @Override
    public boolean update() {
        long currentTime = System.currentTimeMillis();
        if(currentTime >= startTime && currentTime >= startTime + activations * period) {
            runnable.run();
            activations++;
        }
        return false;
    }
}
