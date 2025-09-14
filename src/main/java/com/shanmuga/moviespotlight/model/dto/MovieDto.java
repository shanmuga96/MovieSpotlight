package com.shanmuga.moviespotlight.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigInteger;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    /** Unique Identifier for the movie */
    private String movieId;

    /** Title of the movie */
    private String title;

    /** Movie's year of release */
    private int releaseYear;

    /** Box office earnings in USD. */
    private BigInteger boxOfficeCollectionUsd;

    /** Movie rating (G, PG, PG13, R, etc.) */
    private String rated;

    /** Movie release date */
    private String released;

    /** Duration of the movie */
    private String runtime;

    /** Movie's Genre (Action, Crime, Thriller, etc.) */
    private String genre;

    /** Movie's director(s) */
    private String director;

    /** Movie's writer(s) */
    private String writer;

    /** Main actors starring in the movie */
    private String actors;

    /** A brief story of Movie */
    private String plot;

    /** Original language in which the movie was released */
    private String language;

    /** Country of origin */
    private String country;

    /** Awards bagged by the movie */
    private String awards;

    /** Link to the movie's poster */
    private String poster;

    /** Metascore rating */
    private String metaScore;

    /** IMDB rating for the movie */
    private String imdbRating;

    /** Total no.of IMDB votes for the movie */
    private String imdbVotes;

    /** Link to the movie's poster */
    private String imdbId;

    /** Type of the picture (series, movie, etc.) */
    private String type;

    /** Link to the movie's poster */
    private String dvd;

    /** Link to the movie's poster */
    private String production;

    /** Link to the movie's poster */
    private String website;
}
