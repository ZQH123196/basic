package com.example.webservlet.interception;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.annotation.WebServlet;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class MvcInterceptionApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MvcInterceptionApplication.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;




    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
