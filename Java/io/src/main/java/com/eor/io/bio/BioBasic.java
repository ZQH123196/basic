package com.eor.io.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioBasic {
    public static void main(String[] args) throws Exception {
        localBio();
        netBio();
    }

    public static void localBio() throws Exception {
        InputStream inputStream = new FileInputStream(new File("1k.txt"));
        byte[] data = new byte[1024];
        // 此处堵塞 cpu
        inputStream.read(data);
    }

    public static void netBio() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8999);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[1024];
            inputStream.read(data);
        }
    }
}
