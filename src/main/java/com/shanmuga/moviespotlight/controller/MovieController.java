package com.shanmuga.moviespotlight.controller;

import com.shanmuga.moviespotlight.client.omdb.dto.MovieDetailsResponse;
import com.shanmuga.moviespotlight.model.dto.MovieDto;
import com.shanmuga.moviespotlight.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/** REST Controller with endpoints for listing movies from OMDB API, finding best-picture winner,
 * movie details from the OMDB API based on title, etc. */

@Slf4j
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Get detailed movie information from OMDB API.
     *
     * @param title movie title
     * @return OMDB API response
     */
    @GetMapping("/search")
    public ResponseEntity<MovieDetailsResponse> getMovieDetailsByTitle(
            @RequestParam(value = "title") final String title
    ) {
        log.info("Fetching details from OMDB for movie '{}'", title);
        return ResponseEntity.ok(movieService.getMovieDetails(title));
    }

    /**
     * Check if a movie won Best Picture.
     *
     * @param title movie title
     * @param year  release year (optional)
     * @return boolean flag wrapped in response
     */
    @GetMapping("/{title}/best-picture")
    public ResponseEntity<Boolean> hasWonBestPicture(
            @PathVariable final String title,
            @RequestParam(name = "year", required = false) Integer year
    ) {
        log.info("Checking if '{}' ({}) won Best Picture", title, year);
        boolean hasWon = movieService.hasWonBestPicture(title, year);
        return ResponseEntity.ok(hasWon);
    }

    /**
     * (Optional) Fetch a movie from DB by title.
     *
     * @param title movie title
     * @return MovieDto if found
     */
    @GetMapping("/{title}")
    public ResponseEntity<MovieDto> getMovieFromDb(@PathVariable final String title) {
        log.info("Fetching movie '{}' from DB", title);
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
    }

}
