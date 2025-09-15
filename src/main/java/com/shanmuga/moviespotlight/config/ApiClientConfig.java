package com.shanmuga.moviespotlight.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Configuration
public class ApiClientConfig {

    @Value("${omdb.api.url}")
    private String omdbApiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    @Bean(name = "omdbApiRestClient")
    public RestClient omdbApiRestClient(RestClient.Builder builder) {
        return builder.baseUrl(omdbApiUrl).defaultUriVariables(Map.of("apikey", apiKey)).build();
    }

}
