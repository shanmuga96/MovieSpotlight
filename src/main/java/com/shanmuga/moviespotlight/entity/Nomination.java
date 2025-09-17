package com.shanmuga.moviespotlight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

/** Entity representing movies nominated for the Oscar Awards. Contains information
 * related to the movie such as release year, category of a nomination, and if the
 * movie has won or not */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Nomination {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false,updatable = false)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id") /** Indicates the Foreign Key */
    private Movie movie;

    @Column(nullable = false)
    private int releaseYear;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String nominee;

    @Column(nullable = false)
    private boolean hasWon;

    @Column(nullable = false)
    private String additionalInfo;
}
