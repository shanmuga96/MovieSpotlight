package com.shanmuga.moviespotlight.service;

import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.entity.Rating;
import com.shanmuga.moviespotlight.mapper.MovieMapper;
import com.shanmuga.moviespotlight.model.dto.MovieDto;
import com.shanmuga.moviespotlight.model.dto.RatingDto;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository){
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void addRating(Long movieId, RatingDto ratingDto) {
        Movie movie = movieRepository.findById(BigInteger.valueOf(movieId))
                .orElseThrow(() -> new RuntimeException("Movie not found with id=" + movieId));

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setRating(ratingDto.getRating());
        rating.setComment(ratingDto.getComment());

        ratingRepository.save(rating);
        log.info("Rating saved for movie {}: {}", movie.getTitle(), rating.getRating());
    }

    public List<MovieDto> getTopRatedMovies() {
        log.info("Fetching top 10 movies sorted by rating and box office collection");

        return movieRepository.findAll().stream()
                .sorted(Comparator
                        .comparingDouble((Movie movie) -> movie.getRatings().stream()
                                .mapToInt(Rating::getRating)
                                .average()
                                .orElse(0.0))
                        .thenComparing(Movie::getBoxOfficeCollectionUsd, Comparator.nullsLast(Comparator.reverseOrder()))
                        .reversed()
                )
                .limit(10)
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }

    private double calculateAverageRating(Movie movie) {
        return movie.getRatings().stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
    }
}
