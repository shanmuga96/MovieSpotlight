package com.shanmuga.moviespotlight.client.omdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    /** The name of the rating source (Rotten Tomatoes, Metacritic, etc.)  */
    @JsonProperty("Source")
    private String source;

    /** The rating value as provided by the source (83/100, 7.9/10, etc.) */
    @JsonProperty("Value")
    private String value;
}
