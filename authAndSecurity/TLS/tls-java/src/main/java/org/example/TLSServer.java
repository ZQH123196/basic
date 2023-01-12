package org.example;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class TLSServer {

    public static void main(String[] args) throws Exception {
        // 获取证书实例
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream certificateStream = TLSServer.class.getClassLoader().getResourceAsStream("keystore.pfx");
        //证书密码
        char[] certificatePassword = "1008610086".toCharArray();
        // 加载 证书 到 PKCS12 格式的 keystore 中
        keyStore.load(certificateStream, certificatePassword);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, certificatePassword);


        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        System.out.println(sslContext.getProtocol());

        ServerSocket serverSocket = sslContext.getServerSocketFactory().createServerSocket(8999);

        System.out.println("server wait to accept!");
        Socket accept = serverSocket.accept();
        System.out.println("server accept!");

        InputStream inputStream = accept.getInputStream();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s = bfr.readLine();
        System.out.println("客户端传来："+s);
        serverSocket.close();
    }
}
