package com.graphics.engine;

public class Timer {
    private double lastLoopTime;

    /**
     * Initialize the timer, and set last loop time to the current time
     */
    public void init() {
        lastLoopTime = getTime();
    }

    /**
     * @return the current time as a double 1000 seconds
     */
    public double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }


    /**
     * @return the elapsed time since the lastLoopTime
     */
    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    /**
     * @return the last loop time
     */
    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
