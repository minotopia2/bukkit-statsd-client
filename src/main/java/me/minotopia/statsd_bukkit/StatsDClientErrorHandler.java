package me.minotopia.statsd_bukkit;

/**
 * Describes a handler capable of processing exceptions that occur during StatsD client operations.
 * 
 * @author Tom Denley
 *
 */
@FunctionalInterface
public interface StatsDClientErrorHandler {

    /**
     * Handle the given exception, which occurred during a StatsD client operation.
     * 
     * @param exception
     *     the {@link Exception} that occurred
     */
    void handle(Exception exception);

}
