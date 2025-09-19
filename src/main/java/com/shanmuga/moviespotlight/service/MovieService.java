package com.shanmuga.moviespotlight.service;


import com.shanmuga.moviespotlight.client.omdb.OmdbApiClient;
import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.mapper.MovieMapper;
import com.shanmuga.moviespotlight.model.dto.MovieDto;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.repository.NominationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MovieService{

   private final OmdbApiClient omdbApiClient;
   private final NominationRepository nominationRepository;
   private final MovieRepository movieRepository;

   public MovieService(OmdbApiClient omdbApiClient, NominationRepository nominationRepository, MovieRepository movieRepository){
       this.omdbApiClient = omdbApiClient;
       this.nominationRepository = nominationRepository;
       this.movieRepository = movieRepository;
   }

   public MovieDetailsResponse getMovieDetails(String title){
       return omdbApiClient.getMovieDetailsByTitle(title);
   }

    public boolean hasWonBestPicture(String title, int year) {
        return nominationRepository
                .existsByNomineeIgnoreCaseAndReleaseYearAndHasWonTrueAndCategoryIgnoreCase(
                        title, year, "BEST PICTURE");
    }

    public MovieDto findMovieByTitle(String title) {
        log.debug("Looking up movie '{}' in DB", title);
        Optional<Movie> movieOpt = movieRepository.findByTitleIgnoreCase(title);
        return movieOpt.map(MovieMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + title));
    }
}