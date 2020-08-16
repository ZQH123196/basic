package com.eor.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// 心跳包、短线重连、粘包、拆包

// 发送数据时只通过 write() 方法，接收时为何需要多个 read() 方法?
// TCP 协议无法确定在 read() 和 write() 方法中所发送信息的界限，而且发送过程中可能存在乱序现象，即分割成多个部分，所以无法通过一次 read() 获取到全部数据信息。

// TCP 是基于流的无界传输，即本身不解决数据边界问题，会产生粘包、拆包的情况
// 此处使用 \n 来充当边界，这是为了偷懒使用 readline

public class Server {


    static int ServerPORT = 10001;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.Server_main();

    }

    public void Server_main() throws IOException {
        // 服务器永远监听自己的 IP
        ServerSocket serverSocket = new ServerSocket(ServerPORT);
        Socket clientSoekct = serverSocket.accept();

        new Thread(new Server_listen(clientSoekct)).start();

    }

    class Server_listen implements Runnable {
        private Socket clientSoekct = null;

        Server_listen (Socket clientSoekct){
            this.clientSoekct = clientSoekct;
        }

        public void run() {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(clientSoekct.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    System.out.println(bufferedReader.readLine());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSoekct.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
