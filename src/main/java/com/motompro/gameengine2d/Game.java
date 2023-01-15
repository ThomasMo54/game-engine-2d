package com.motompro.gameengine2d;

public abstract class Game {

    private static final double SECOND_IN_NANO = 1000000000;
    private static final double MILLISECOND_IN_NANO = 1000000;
    private static final double DEFAULT_FRAME_RATE = 240;

    private boolean running = false;
    private double frameRate = DEFAULT_FRAME_RATE;

    private void doGameLoop() {
        long lastTick = 0;
        double deltaTime;

        while(running) {
            deltaTime = (System.nanoTime() - lastTick) / SECOND_IN_NANO;
            update(deltaTime);
            render();
            lastTick = System.nanoTime();
            try {
                Thread.sleep((long) (((SECOND_IN_NANO / frameRate) - deltaTime) / MILLISECOND_IN_NANO));
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
        this.frameRate = framesPerSecond;
    }

    public double getFrameRateLimit() {
        return frameRate;
    }

    public abstract void update(double deltaTime);

    public abstract void render();
}
