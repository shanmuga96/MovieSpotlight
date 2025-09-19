package com.shanmuga.moviespotlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** Represents a movie entity. */
@Entity
@Getter /** getter methods generated at compile time for all fields */
@Setter /** setter methods generated at compile time for all fields */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id /** Primary Key */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false)
    private BigInteger id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int releaseYear;

    @Column
    private BigInteger boxOfficeCollectionUsd;

    @Column
    private String rated;

    @Column
    private String released;

    @Column
    private String runtime;

    @Column
    private String genre;

    @Column
    private String director;

    @Column
    private String writer;

    @Column
    private String actors;

    @Column
    private String plot;

    @Column
    private String language;

    @Column
    private String country;

    @Column
    private String awards;

    @Column
    private String poster;

    @Column
    private String metaScore;

    @Column
    private String imdbRating;

    @Column
    private String imdbVotes;

    @Column
    private String imdbId;

    @Column
    private String type;

    @Column
    private String dvd;

    @Column
    private String production;

    @Column
    private String website;

    /** A movie can have many ratings */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
}
