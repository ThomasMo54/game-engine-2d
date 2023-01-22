package com.motompro.gameengine2d.manager.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TaskManager {

    private int currentId = 0;
    private final Map<Integer, Task> tasks = new HashMap<>();

    public void update() {
        tasks.values().removeIf(Task::update);
    }

    public void cancelTask(int taskId) {
        tasks.remove(taskId);
    }

    public int scheduleDelayedTask(Runnable runnable, double delay, TimeUnit timeUnit) {
        int id = currentId;
        tasks.put(id, new Task(runnable, delay, timeUnit));
        currentId++;
        return id;
    }

    public int scheduleRepeatingTask(Runnable runnable, double delay, double period, TimeUnit timeUnit) {
        int id = currentId;
        tasks.put(id, new RepeatingTask(runnable, delay, period, timeUnit));
        currentId++;
        return id;
    }

    public int scheduleRepeatingTask(Runnable runnable, double period, TimeUnit timeUnit) {
        return scheduleRepeatingTask(runnable, 0, period, timeUnit);
    }

    public int scheduleAsyncDelayedTask(Runnable runnable, double delay, TimeUnit timeUnit) {
        int id = currentId;
        tasks.put(id, new AsyncTask(runnable, delay, timeUnit));
        currentId++;
        return id;
    }

    public int scheduleAsyncRepeatingTask(Runnable runnable, double delay, double period, TimeUnit timeUnit) {
        int id = currentId;
        tasks.put(id, new AsyncRepeatingTask(runnable, delay, period, timeUnit));
        currentId++;
        return id;
    }

    public int scheduleAsyncRepeatingTask(Runnable runnable, double period, TimeUnit timeUnit) {
        return scheduleAsyncRepeatingTask(runnable, 0, period, timeUnit);
    }
}
