package com.example.sbsourcelearning.tmp;

import org.junit.jupiter.api.Test;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Tmp {
    @Test
    public void test1() throws SocketException, UnknownHostException {
        String ip;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        }
        System.out.println(ip);
    }
}
