package com.shanmuga.moviespotlight.repository;

import com.shanmuga.moviespotlight.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, BigInteger> {
    List<Movie> findMovieByTitleAndReleaseYear(final String title, final int releaseYear);

    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByTitleIgnoreCase(String title);
}
