package com.shanmuga.moviespotlight.mapper;

import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.entity.Rating;
import com.shanmuga.moviespotlight.model.dto.RatingDto;

public class RatingMapper {

    public static RatingDto toDto(Rating rating) {
        if (rating == null) return null;

        return new RatingDto(
                rating.getRating(),
                rating.getMovie() != null ? rating.getMovie().getTitle() : null,
                null, // userName (to be added once User entity exists)
                rating.getComment()
        );
    }

    public static Rating toEntity(RatingDto dto, Movie movie) {
        if (dto == null) return null;

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setRating(dto.getRating());
        rating.setComment(dto.getComment());

        return rating;
    }
}

