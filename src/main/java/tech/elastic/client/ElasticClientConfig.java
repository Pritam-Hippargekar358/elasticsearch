package tech.elastic.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientOptions;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ElasticClientConfig {

    @Bean
    public RestClient restClient() throws IOException {
        String serverUrl = "http://localhost:9200";
        String userName = "elastic";
        String passWord = "=3AV5b=0HiEMrhNDpRBP";
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,  new UsernamePasswordCredentials(userName, passWord) );

//        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
//                .setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credentialsProvider))
//                .build();

       RestClient restClient = RestClient.builder(HttpHost.create(serverUrl))
               .setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credentialsProvider))
               .build();

        return restClient;
    }

    @Bean
    public RestClientTransport restClientTransport(RestClient restClient, ObjectProvider<RestClientOptions> restClientOptions) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Disable writing dates as timestamps (write dates in ISO-8601 format instead)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonpMapper jsonpMapper = new JacksonJsonpMapper(objectMapper);

//        ObjectMapper mapper = new ObjectMapper()
//                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .registerModule(new JavaTimeModule());
        return new RestClientTransport(restClient, jsonpMapper, restClientOptions.getIfAvailable());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(RestClientTransport restClientTransport) throws IOException {
        ElasticsearchClient client =  new ElasticsearchClient(restClientTransport);
        //to check Elastic server health and connection status
        HealthResponse healthResponse = client.cluster().health();
        System.out.println("Elasticsearch status is: "+ healthResponse.status());
        return client;
    }
//
//    https://github.com/arafkarsh/ms-springboot-334-vanilla/blob/main/src/main/java/io/fusion/air/microservice/security/CryptoKeyGenerator.java//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .findAndRegisterModules();
//    }
}
