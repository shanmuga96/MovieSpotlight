package com.shanmuga.moviespotlight.controller;

import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
