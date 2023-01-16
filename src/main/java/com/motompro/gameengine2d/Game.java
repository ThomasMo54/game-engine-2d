package com.motompro.gameengine2d;

/**
 * This class represents the main class of a game app. It handles the game loop and frame rate tricks.
 */
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

    /**
     * Start the game loop. Do nothing if the game is already running.
     */
    public void run() {
        if(running)
            return;
        running = true;
        doGameLoop();
    }

    /**
     * Stop the game loop.
     */
    public void stop() {
        running = false;
    }

    /**
     * @return The game loop state
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * The frame rate limit is used to limit the game loop speed. Set to {@value #DEFAULT_FRAME_RATE} fps by default.
     * @param limit Frame rate limit (in frames per second)
     */
    public void setFrameRateLimit(double limit) {
        this.frameRateLimit = Math.max(1, limit);
    }

    /**
     * @return The active frame rate limit (in frames per second)
     */
    public double getFrameRateLimit() {
        return frameRateLimit;
    }

    /**
     * @return The current frame rate the game is running at (in frames per second)
     */
    public double getCurrentFrameRate() {
        return currentFrameRate;
    }

    /**
     * This method is called at every game loop iteration. Game logic calculations should be done here.
     * @param deltaTime The time elapsed since the last game loop iteration (in seconds)
     */
    protected abstract void update(double deltaTime);

    /**
     * This method is called at every game loop iteration. Game rendering should be done here.
     */
    protected abstract void render();
}
