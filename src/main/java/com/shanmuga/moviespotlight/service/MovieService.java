package com.shanmuga.moviespotlight.service;


import com.shanmuga.moviespotlight.client.omdb.OmdbApiClient;
import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieService{

   private final OmdbApiClient omdbApiClient;
   private final MovieRepository movieRepository;

   public MovieService(OmdbApiClient omdbApiClient, MovieRepository movieRepository){
       this.omdbApiClient = omdbApiClient;
       this.movieRepository = movieRepository;
   }

   public MovieDetailsResponse getMovieDetails(String title){
       log.info("printing response {}",omdbApiClient.getMovieDetailsByTitle(title));
       return omdbApiClient.getMovieDetailsByTitle(title);
   }
}