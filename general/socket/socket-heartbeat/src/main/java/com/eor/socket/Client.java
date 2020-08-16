package com.eor.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

// 重连只在心跳失效后
// socket close
// Once a socket has been closed, it is not available for further networking use (i.e. can't be reconnected or rebound). A new socket needs to be created.

public class Client {

    static String TargetIP = "127.0.0.1";
    static int TargetPORT = 10001;
    static int timeout = 1000;

    static SocketAddress serverAddress = new InetSocketAddress(TargetIP, TargetPORT);
    static boolean connection_state = false;

    private static Socket clientSocket = null;

    public static void main(String[] args) throws IOException {

        while (!connection_state) {
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void connect() {
        try {
            clientSocket = new Socket();
            clientSocket.connect(serverAddress, timeout);
            System.out.println("连接服务器成功！");
            connection_state = true;

            OutputStream outputStream = clientSocket.getOutputStream();
            InputStream inputStream = clientSocket.getInputStream();


            new Thread(new Heart_beat(clientSocket, outputStream)).start();



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            connection_state = false;
            e.printStackTrace();
        }
    }

    public static void reconnect() {
        while (!connection_state){
            System.out.println("正在尝试重新链接.....");
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    static class Heart_beat implements Runnable {
        Socket clientSocket = null;
        OutputStream outputStream = null;

        Heart_beat(Socket clientSocket, OutputStream outputStream) {
            this.clientSocket = clientSocket;
            this.outputStream = outputStream;
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("正在发送心跳包......");
                    String str = "10086\n";
                    this.outputStream.write(str.getBytes("UTF-8"), 0, str.getBytes("UTF-8").length);
                    Thread.sleep(5000);
                }
            } catch (IOException e) { // 心跳 IO 发送出错，说明链接断开
                try {
                    clientSocket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                connection_state = false;
                reconnect();
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }




}
