package com.motompro.gameengine2d;

public abstract class Game {

    private static final double SECOND_IN_NANO = 1000000000;
    private static final double MILLISECOND_IN_NANO = 1000000;
    private static final double DEFAULT_FRAME_RATE = 240;

    private boolean running = false;
    private double frameRateLimit = DEFAULT_FRAME_RATE;
    private double currentFrameRate = 0;

    private void doGameLoop() {
        long lastTick = System.nanoTime();
        double deltaTime;

        while(running) {
            deltaTime = (System.nanoTime() - lastTick) / SECOND_IN_NANO;
            currentFrameRate = 1 / deltaTime;
            update(deltaTime);
            render();
            lastTick = System.nanoTime();
            try {
                Thread.sleep((long) (((SECOND_IN_NANO / frameRateLimit) - deltaTime) / MILLISECOND_IN_NANO));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() {
        running = true;
        doGameLoop();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setFrameRateLimit(double framesPerSecond) {
        this.frameRateLimit = Math.max(1, framesPerSecond);
    }

    public double getFrameRateLimit() {
        return frameRateLimit;
    }

    public double getCurrentFrameRate() {
        return currentFrameRate;
    }

    public abstract void update(double deltaTime);

    public abstract void render();
}
