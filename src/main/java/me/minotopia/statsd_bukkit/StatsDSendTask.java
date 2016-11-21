package me.minotopia.statsd_bukkit;

import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A Bukkit task that maintains a queue of messages to send to Statsd and sends them periodically.
 *
 * @author <a href="https://l1t.li/">Literallie</a>
 * @since 2016-21-11
 */
public class StatsDSendTask extends BukkitRunnable {
    private final Deque<byte[]> sendDeque = new ConcurrentLinkedDeque<>();
    private final DatagramChannel clientSocket;
    private final StatsDClientErrorHandler errorHandler;

    public StatsDSendTask(DatagramChannel clientSocket, StatsDClientErrorHandler errorHandler) {
        this.clientSocket = clientSocket;
        this.errorHandler = errorHandler;
    }

    public void queue(byte[] message) {
        sendDeque.add(message);
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            if(sendDeque.isEmpty()) {
                return;
            }
            byte[] message = sendDeque.remove();
            try {
                clientSocket.write(ByteBuffer.wrap(message));
            } catch (IOException e) {
                errorHandler.handle(e);
                return; //continue to next tick maybe it will be fixed by then
            }
        }
    }
}
