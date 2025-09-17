package com.shanmuga.moviespotlight.service;


import com.shanmuga.moviespotlight.client.omdb.OmdbApiClient;
import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.repository.NominationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieService{

   private final OmdbApiClient omdbApiClient;
   private final NominationRepository nominationRepository;

   public MovieService(OmdbApiClient omdbApiClient, NominationRepository nominationRepository){
       this.omdbApiClient = omdbApiClient;
       this.nominationRepository = nominationRepository;
   }

   public MovieDetailsResponse getMovieDetails(String title){
       return omdbApiClient.getMovieDetailsByTitle(title);
   }

    public boolean hasWonBestPicture(String title, int year) {
        return nominationRepository
                .existsByNomineeIgnoreCaseAndReleaseYearAndHasWonTrueAndCategoryIgnoreCase(
                        title, year, "BEST PICTURE");
    }
}