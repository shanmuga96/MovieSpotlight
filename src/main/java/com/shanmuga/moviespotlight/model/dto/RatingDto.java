package com.shanmuga.moviespotlight.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    /** Numerical rating value given to the movie (1-10) */
    private int rating;

    /** Title of the movie */
    private String movieTitle;

    /** Name of the user who provided the rating */
    private String userName;

    /** (Optional) Comments posted by user along with the rating */
    private String comment;
}
