package com.shanmuga.moviespotlight.controller;

import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.repository.NominationRepository;
import com.shanmuga.moviespotlight.service.AcademyAwardsLoader;
import com.shanmuga.moviespotlight.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public MovieDetailsResponse getMovieDetailsByTitle(@RequestParam(value = "title") final String title) {
        log.info("Into get movies by title controller");
        return movieService.getMovieDetails(title);
    }

    @GetMapping("/{title}/best-picture")
    public ResponseEntity<Map<String, Object>> hasWonBestPicture(
            @PathVariable final String title,
            @RequestParam(name = "year",required = false) Integer year) {
        log.info("Checking if '{}' ({}) won Best Picture", title, year);

        boolean hasWon = movieService.hasWonBestPicture(title, year);

        return ResponseEntity.ok(
                Map.of(
                        "title", title,
                        "year", year,
                        "bestPictureWinner", hasWon
                )
        );
    }

}
