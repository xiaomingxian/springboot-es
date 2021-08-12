package com.edianyun.workorder.order2es.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
@Slf4j
public class EsConfig {


    @Bean
    public RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("es-test-data01.edianzuno.cn:9999")
                .build();


        RestHighLevelClient rest = RestClients.create(clientConfiguration).rest();

        log.info("===================  RestHighLevelClient 创建成功  ======================");

        return rest;
    }
}
