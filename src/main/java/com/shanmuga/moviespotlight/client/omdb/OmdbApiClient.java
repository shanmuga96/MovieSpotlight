package com.shanmuga.moviespotlight.client.omdb;

import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class OmdbApiClient {

    private final RestClient omdbApiRestClient;

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    public OmdbApiClient(@Qualifier("omdbApiRestClient") final RestClient omdbApiRestClient) {
        this.omdbApiRestClient = omdbApiRestClient;
    }

    public MovieDetailsResponse getMovieDetailsByTitle(final String title) {
        log.info("Into get movie details by title : {}",title);
        return omdbApiRestClient.get().uri(uriBuilder -> uriBuilder.queryParam("t", title).queryParam("apikey", apiKey).build())
                .accept(MediaType.APPLICATION_JSON).retrieve().body(MovieDetailsResponse.class);
    }

    public MovieDetailsResponse getMovieDetailsByTitleAndYear(final String title,final int year) {
        log.info("Into get movie details by title :{} and year :{}",title,year);
        return omdbApiRestClient.get().uri(uriBuilder -> uriBuilder.queryParam("t", title).queryParam("y",year).queryParam("apikey", apiKey).build())
                .accept(MediaType.APPLICATION_JSON).retrieve().body(MovieDetailsResponse.class);
    }
}
