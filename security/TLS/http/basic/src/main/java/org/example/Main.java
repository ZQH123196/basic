package org.example;

public class Main {
    public static void main(String[] args) {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        class CostomX509 implements X509TrustManager {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

            @Override
            public void  checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }
        X509TrustManager trustManager = new CostomX509();
        X509TrustManager[] trustManagers = {trustManager};
        sslContext.init(null, trustManagers, null);
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> registryNew = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf.getSocketFactory())
                .build();
//创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registryNew);
//设置最大连接
        cm.setMaxTotal(100);
//设置每个主机（域名）的最大连接数（并发量大时，避免某一个使用过大而其他过小）
        cm.setDefaultMaxPerRoute(10);
    }
}