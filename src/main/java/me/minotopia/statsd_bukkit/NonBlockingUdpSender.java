package me.minotopia.statsd_bukkit;

import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public final class NonBlockingUdpSender {
    private final Charset encoding;
    private final DatagramChannel clientSocket;
    private final Plugin plugin;
    private final StatsDClientErrorHandler handler;

    public NonBlockingUdpSender(String hostname, int port, Charset encoding, StatsDClientErrorHandler handler, Plugin plugin) throws IOException {
        this.encoding = encoding;
        this.handler = handler;
        this.plugin = plugin;
        this.clientSocket = DatagramChannel.open();
        this.clientSocket.connect(new InetSocketAddress(hostname, port));
    }

    public void send(final String message) {
        if(plugin.isEnabled()) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> blockingSend(message));
        }
    }

    private void blockingSend(String message) {
        try {
            final byte[] sendData = message.getBytes(encoding);
            clientSocket.write(ByteBuffer.wrap(sendData));
        } catch (Exception e) {
            handler.handle(e);
        }
    }
}