package com.shanmuga.moviespotlight.repository;

import com.shanmuga.moviespotlight.entity.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, BigInteger> {

    @Query("SELECT r.movie.id, AVG(r.rating) as avgRating " +
            "FROM Rating r " +
            "GROUP BY r.movie.id " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopMoviesByAverageRating(Pageable pageable);
}
