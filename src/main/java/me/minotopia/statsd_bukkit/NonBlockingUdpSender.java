package me.minotopia.statsd_bukkit;

import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public final class NonBlockingUdpSender {
    private final Charset encoding;
    private final StatsDSendTask sendTask;
    private final Plugin plugin;

    public NonBlockingUdpSender(String hostname, int port, Charset encoding, StatsDClientErrorHandler handler, Plugin plugin) throws IOException {
        this.encoding = encoding;
        this.plugin = plugin;
        DatagramChannel clientSocket = DatagramChannel.open();
        clientSocket.connect(new InetSocketAddress(hostname, port));
        sendTask = new StatsDSendTask(clientSocket, handler);
        sendTask.runTaskTimerAsynchronously(plugin, 5L, 5L);
    }

    public void send(final String message) {
        if (plugin.isEnabled()) {
            sendTask.queue(message.getBytes(encoding));
        }
    }
}