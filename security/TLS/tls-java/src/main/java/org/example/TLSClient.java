package org.example;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;

public class TLSClient {

    public static void main(String[] args) throws Exception {
        // 获取证书实例
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream certificateStream = TLSServer.class.getClassLoader().getResourceAsStream("keystore.pfx");
        //证书密码
        char[] certificatePassword = "1008610086".toCharArray();
        // 加载 证书 到 PKCS12 格式的 keystore 中
        keyStore.load(certificateStream, certificatePassword);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("sunx509");
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

        Socket socket = sslContext.getSocketFactory().createSocket();
        SocketAddress socketAddress = new InetSocketAddress("localhost", 8999);
        socket.connect(socketAddress);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是客户端，通过 tls 请求服务器。".getBytes("UTF-8"));
        socket.close();

    }
}
