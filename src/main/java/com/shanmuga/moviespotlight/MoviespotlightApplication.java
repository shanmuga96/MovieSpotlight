package com.shanmuga.moviespotlight;

import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.entity.Rating;
import com.shanmuga.moviespotlight.repository.MovieRepository;
import com.shanmuga.moviespotlight.repository.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;

@SpringBootApplication
public class MoviespotlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviespotlightApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(MovieRepository movieRepository,
								   RatingRepository ratingRepository) {
		return args -> {
			Movie movie = new Movie();
			movie.setTitle("The Test Movie");
			movie.setBoxOfficeCollectionUsd(BigInteger.valueOf(100000000));
//			movie.setIsBestPictureWinner(false);
			movie.setReleaseYear(2024);

			movie = movieRepository.save(movie);

			Rating rating = new Rating();
			rating.setMovie(movie);
//			rating.setScore(5);
//			rating.setCreatedAt(LocalDateTime.now());

			ratingRepository.save(rating);

			System.out.println("🎬 Inserted Movie: " + movie.getTitle());
			System.out.println("⭐ Inserted Rating: " + 5);
		};
	}
}
