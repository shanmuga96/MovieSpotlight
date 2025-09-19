package com.shanmuga.moviespotlight.service;

import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.entity.Nomination;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.repository.NominationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AcademyAwardsLoader implements CommandLineRunner {
    private static final String BEST_PICTURE = "BEST PICTURE";

    private final MovieRepository movieRepository;
    private final NominationRepository nominationRepository;

    private String csvPath = "db/data/academy_awards.csv";

    @Override
    public void run(String... args){
        log.info("=== Academy Awards Loader Startup ===");
        loadOscarNominations();
    }

    /**
     * Loads Academy Award nominations from a CSV file and saves Best Picture nominations to the database.
     */
    @Transactional
    public void loadOscarNominations() {
        log.info("Starting Academy Awards CSV loading from {}", csvPath);

        try (var reader = getCsvReader()) {
            List<Nomination> nominations = reader.lines()
                    .skip(1) // skip header
                    .map(this::parseCsvLine)
                    .filter(data -> data.length >= 5 && BEST_PICTURE.equalsIgnoreCase(data[1].trim()))
                    .map(this::createNomination)
                    .filter(Objects::nonNull)
                    .toList();

            nominationRepository.saveAll(nominations);
            log.info("Saved {} Best Picture nominations", nominations.size());
        } catch (Exception e) {
            log.error("Error loading Academy Awards CSV", e);
        }
    }

    /**
     * Creates a Nomination entity from CSV data.
     */
    private Nomination createNomination(String[] data) {
        try {
            String yearText = data[0].trim();             // "2010 (83rd)"
            String yearOnly = yearText.substring(0, 4);  // "2010"
            int releaseYear = Integer.parseInt(yearOnly);

            String nominee = data[2].trim();
            String additionalInfo = data[3].trim();
            boolean hasWon = "YES".equalsIgnoreCase(data[4].trim());

            Movie movie = findOrCreateMovie(nominee, releaseYear);

            return Nomination.builder()
                    .movie(movie)
                    .category(BEST_PICTURE)
                    .releaseYear(releaseYear)
                    .nominee(nominee)
                    .additionalInfo(additionalInfo)
                    .hasWon(hasWon)
                    .build();

        } catch (Exception e) {
            log.warn("Skipping invalid CSV line: {} - {}", String.join(",", data), e.getMessage());
            return null;
        }
    }

    /**
     * Finds an existing movie or creates a new one.
     */
    private Movie findOrCreateMovie(String title, int releaseYear) {
        Optional<Movie> movieOpt = movieRepository.findMovieByTitleAndReleaseYear(title, releaseYear).stream().findFirst();
        return movieOpt.orElseGet(() -> movieRepository.save(
                Movie.builder()
                        .title(title)
                        .releaseYear(releaseYear)
                        .boxOfficeCollectionUsd(BigInteger.ZERO)
                        .build()
        ));
    }

    /**
     * Reads the CSV file from classpath.
     */
    private BufferedReader getCsvReader() {
        var inputStream = getClass().getClassLoader().getResourceAsStream(csvPath);
        if (inputStream == null) {
            throw new RuntimeException("CSV file not found: " + csvPath);
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Parses a CSV line into an array of Strings, handling quoted values.
     */
    private String[] parseCsvLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }


}
