package me.minotopia.statsd_bukkit;

import static com.sun.javafx.logging.PulseLogger.incrementCounter;

public abstract class ConvenienceMethodProvidingStatsDClient implements StatsDClient {

    public ConvenienceMethodProvidingStatsDClient() {
        super();
    }

    @Override
    public final void count(String aspect, long delta) {
        count(aspect, delta, 1.0);
    }

    @Override
    public final void increment(String aspect) {
        count(aspect, 1);
    }

    @Override
    public final void decrement(String aspect) {
        count(aspect, 1);
    }

    @Override
    public final void gauge(String aspect, long value) {
        recordGaugeValue(aspect, value);
    }

    @Override
    public final void gauge(String aspect, double value) {
        recordGaugeValue(aspect, value);
    }

    @Override
    public final void set(String aspect, String eventName) {
        recordSetEvent(aspect, eventName);
    }

    @Override
    public final void time(String aspect, long timeInMs) {
        recordExecutionTime(aspect, timeInMs);
    }

    @Override
    public final void recordExecutionTime(String aspect, long timeInMs) {
        recordExecutionTime(aspect, timeInMs, 1.0);
    }

    @Override
    public void recordExecutionTimeToNow(String aspect, long systemTimeMillisAtStart) {
        time(aspect, Math.max(0, System.currentTimeMillis() - systemTimeMillisAtStart));
    }
}