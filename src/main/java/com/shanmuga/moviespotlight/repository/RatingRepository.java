package com.shanmuga.moviespotlight.repository;

import com.shanmuga.moviespotlight.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RatingRepository extends JpaRepository<Rating, BigInteger> {

}
