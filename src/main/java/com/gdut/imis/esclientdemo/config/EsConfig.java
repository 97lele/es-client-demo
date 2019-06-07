package com.gdut.imis.esclientdemo.config;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lulu
 * @Date 2019/6/7 18:27
 */
@Configuration
public class EsConfig {
@Value("${es.port}")
private Integer port;
@Value("${es.scheme}")
private String scheme;
@Value("${es.host}")
private String host;

@Bean
public Gson gson(){
    return new Gson();
}


    @Bean
    public RestHighLevelClient client(){
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host,port,scheme)
//这里如果要用client去访问其他节点，就添加进去
                )
        );
        return client;
    }
}
