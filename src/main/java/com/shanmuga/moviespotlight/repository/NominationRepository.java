package com.shanmuga.moviespotlight.repository;

import com.shanmuga.moviespotlight.entity.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface NominationRepository extends JpaRepository<Nomination, BigInteger> {

    boolean existsByNomineeIgnoreCaseAndReleaseYearAndHasWonTrueAndCategoryIgnoreCase(
            String nominee, int releaseYear, String category);
}
