package com.shanmuga.moviespotlight.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/** Entity representing the ratings of a movie.*/
@Entity
@Getter /** getter methods generated at compile time for all fields */
@Setter /** setter methods generated at compile time for all fields */
public class Rating {
    @Id /** Primary Key */
    @Column(name = "id", nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    /** Many ratings can be given for the same movie by different users.
     * (Or) One movie can have many ratings */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id") /** Indicates the Foreign Key */
    private Movie movie;
}
