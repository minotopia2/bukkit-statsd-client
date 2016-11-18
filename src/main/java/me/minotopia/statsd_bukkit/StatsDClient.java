package me.minotopia.statsd_bukkit;

/**
 * Describes a client connection to a StatsD server, which may be used to post metrics
 * in the form of counters, timers, and gauges.
 *
 * <p>Four key methods are provided for the submission of data-points for the application under
 * scrutiny:
 * <ul>
 * <li>{@link #incrementCounter} - adds one to the value of the specified named counter</li>
 * <li>{@link #recordGaugeValue} - records the latest fixed value for the specified named gauge</li>
 * <li>{@link #recordExecutionTime} - records an execution time in milliseconds for the specified named operation</li>
 * <li>{@link #recordSetEvent} - records an occurrence of the specified named event</li>
 * </ul>
 *
 * @author Tom Denley
 */
public interface StatsDClient {

    /**
     * Adjusts the specified counter by a given delta.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the counter to adjust
     * @param delta  the amount to adjust the counter by
     */
    void count(String aspect, long delta);

    /**
     * Adjusts the specified counter by a given delta.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect     the name of the counter to adjust
     * @param delta      the amount to adjust the counter by
     * @param sampleRate the sampling rate being employed. For example, a rate of 0.1 would tell StatsD that this
     *                   counter is being sent sampled every 1/10th of the time.
     */
    void count(String aspect, long delta, double sampleRate);

    /**
     * Increments the specified counter by one.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the counter to increment
     */
    void increment(String aspect);

    /**
     * Decrements the specified counter by one.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the counter to decrement
     */
    void decrement(String aspect);

    /**
     * Records the latest fixed value for the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param value  the new reading of the gauge
     */
    void recordGaugeValue(String aspect, long value);

    /**
     * Records the latest fixed value for the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param value  the new reading of the gauge
     */
    void recordGaugeValue(String aspect, double value);

    /**
     * Records a change in the value of the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param delta  the +/- delta to apply to the gauge
     */
    void recordGaugeDelta(String aspect, long delta);

    /**
     * Records a change in the value of the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param delta  the +/- delta to apply to the gauge
     */
    void recordGaugeDelta(String aspect, double delta);

    /**
     * Records the latest fixed value for the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param value  the new reading of the gauge
     */
    void gauge(String aspect, long value);

    /**
     * Records the latest fixed value for the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect the name of the gauge
     * @param value  the new reading of the gauge
     */
    void gauge(String aspect, double value);

    /**
     * StatsD supports counting unique occurrences of events between flushes, Call this method to records an occurrence
     * of the specified named event.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect    the name of the set
     * @param eventName the value to be added to the set
     */
    void recordSetEvent(String aspect, String eventName);

    /**
     * StatsD supports counting unique occurrences of events between flushes, Call this method to records an occurrence
     * of the specified named event.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect    the name of the set
     * @param eventName the value to be added to the set
     */
    void set(String aspect, String eventName);

    /**
     * Records an execution time in milliseconds for the specified named operation.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect   the name of the timed operation
     * @param timeInMs the time in milliseconds
     */
    void recordExecutionTime(String aspect, long timeInMs);

    /**
     * Records an execution time.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect     the name of the stat to save to
     * @param timeInMs   the execution time in milliseconds
     * @param sampleRate the sampling rate being employed. For example, a rate of 0.1 would tell StatsD that this timer
     *                   is being sent sampled every 1/10th of the time, so that it updates its timer_counters
     *                   appropriately.
     */
    void recordExecutionTime(String aspect, long timeInMs, double sampleRate);

    /**
     * Records an execution time in milliseconds for the specified named operation. The execution
     * time is calculated as the delta between the specified start time and the current system
     * time (using {@link System#currentTimeMillis()})
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect                  the name of the timed operation
     * @param systemTimeMillisAtStart the system time, in millis, at the start of the operation that has just completed
     */
    void recordExecutionTimeToNow(String aspect, long systemTimeMillisAtStart);

    /**
     * Records an execution time.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect     the name of the stat to save to
     * @param timeInMs   the execution time in milliseconds
     *                   appropriately.
     */
    void time(String aspect, long timeInMs);

}