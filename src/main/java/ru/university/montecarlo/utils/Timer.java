package ru.university.montecarlo.utils;

public class Timer {

    private long startTime;
    private long endTime;
    private boolean running;

    public void start() {
        this.startTime = System.nanoTime();
        this.running = true;
    }

    public void stop() {
        if (!running) {
            throw new IllegalStateException("Timer has not been started");
        }

        this.endTime = System.nanoTime();
        this.running = false;
    }

    public void reset() {
        this.startTime = 0L;
        this.endTime = 0L;
        this.running = false;
    }

    public long getElapsedNanoseconds() {
        if (running) {
            return System.nanoTime() - startTime;
        }

        return endTime - startTime;
    }

    public double getElapsedMilliseconds() {
        return getElapsedNanoseconds() / 1_000_000.0;
    }

    public double getElapsedSeconds() {
        return getElapsedNanoseconds() / 1_000_000_000.0;
    }

    @Override
    public String toString() {
        return String.format("Elapsed time: %.3f ms", getElapsedMilliseconds());
    }
}