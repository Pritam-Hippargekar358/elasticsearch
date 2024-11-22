//package tech.elastic.client;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateException;
//import java.security.cert.CertificateFactory;
//
//@Slf4j
//public class ElasticsearchUtil {
//
//    public ElasticsearchClient elasticsearchClient(ElasticConfig elasticConfig) {
//        ElasticsearchTransport transport = getElasticsearchTransport(elasticConfig);
//        return new ElasticsearchClient(transport);
//    }
//
//    private ElasticsearchTransport getElasticsearchTransport(ElasticConfig elasticConfig) {
//        final CredentialsProvider credentialsProvider =   new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticConfig.getUsername(), elasticConfig.getPassword()));
//
//        Path certPath = Paths.get("/Users/apple/IdeaProjects/myproject/elastic-learn/src/main/resources/cert/es01.cer");
//        SSLContext sslContext = null;
//        try {
//            CertificateFactory factory = CertificateFactory.getInstance("X.509");
//            Certificate trustedCa;
//            try (InputStream is = Files.newInputStream(certPath)) {
//                trustedCa = factory.generateCertificate(is);
//            }
//            KeyStore trustStore = KeyStore.getInstance("pkcs12");
//            trustStore.load(null, null);
//            trustStore.setCertificateEntry("ca", trustedCa);
//            SSLContextBuilder sslContextBuilder = SSLContexts.custom()
//                    .loadTrustMaterial(trustStore, null);
//            sslContext = sslContextBuilder.build();
//        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException |
//                 KeyManagementException ex) {
//            log.error("ES", ex);
//        }
//
//        SSLContext finalSslContext = sslContext;
//        RestClientBuilder builder = RestClient.builder(
//                        new HttpHost(elasticConfig.getHostname(), elasticConfig.getPort(), elasticConfig.getSchema()))
//                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                        .setSSLContext(finalSslContext)
//                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                        .setDefaultCredentialsProvider(credentialsProvider));
//        //        String apiKeyId = "SY6uOoABwRrDJxOdlx78";
//        //        String apiKeySecret = "E8Ae8-FgScqT-nXCSBN0ew";
//        //        String apiKeyAuth =
//        //                Base64.getEncoder().encodeToString(
//        //                        (apiKeyId + ":" + apiKeySecret)
//        //                                .getBytes(StandardCharsets.UTF_8));
//        //        Header[] defaultHeaders =
//        //                new Header[]{new BasicHeader("Authorization",
//        //                        "ApiKey " + apiKeyAuth)};
//        //        builder.setDefaultHeaders(defaultHeaders);
//
//        RestClient client = builder.build();
//
//        return new RestClientTransport(client, new JacksonJsonpMapper());
//    }
//
//}
