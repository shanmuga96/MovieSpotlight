package com.shanmuga.moviespotlight.controller;

import com.shanmuga.moviespotlight.model.dto.MovieDto;
import com.shanmuga.moviespotlight.model.dto.RatingDto;
import com.shanmuga.moviespotlight.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST Controller with endpoints for adding a new rating to a movie, retrieving the existing ratings for a movie
 * listing movies based on the ratings */

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    /**
     * Submit a rating for a movie.
     *
     * @param movieId   ID of the movie
     * @param ratingDto rating details
     * @return success message
     */
    @PostMapping("/{movieId}/ratings")
    public ResponseEntity<String> addRating(
            @PathVariable Long movieId,
            @RequestBody RatingDto ratingDto
    ) {
        log.info("Adding rating for movieId={} by {}", movieId, ratingDto.getUserName());
        ratingService.addRating(movieId, ratingDto);
        return ResponseEntity.ok("Rating submitted successfully");
    }

    /**
     * Get top 10 rated movies (sorted by rating and box office).
     *
     * @return list of movies
     */
    @GetMapping("/ratings/top-rated")
    public ResponseEntity<List<MovieDto>> getTopRatedMovies() {
        log.info("Fetching top 10 rated movies");
        return ResponseEntity.ok(ratingService.getTopRatedMovies());
    }
}
