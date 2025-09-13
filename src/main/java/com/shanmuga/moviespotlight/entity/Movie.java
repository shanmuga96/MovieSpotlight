package com.shanmuga.moviespotlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

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
    private String genre;

    @Column
    private String runtime;

    @Column
    private String director;
}
