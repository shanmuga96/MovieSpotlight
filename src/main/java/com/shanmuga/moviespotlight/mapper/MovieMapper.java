package com.shanmuga.moviespotlight.mapper;

import com.shanmuga.moviespotlight.entity.Movie;
import com.shanmuga.moviespotlight.model.dto.MovieDto;

public class MovieMapper {

    public static MovieDto toDto(Movie movie) {
        if (movie == null) return null;

        return MovieDto.builder()
                .movieId(movie.getId() != null ? movie.getId().toString() : null)
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .boxOfficeCollectionUsd(movie.getBoxOfficeCollectionUsd())
                .rated(movie.getRated())
                .released(movie.getReleased())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .actors(movie.getActors())
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .metaScore(movie.getMetaScore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .imdbId(movie.getImdbId())
                .type(movie.getType())
                .dvd(movie.getDvd())
                .production(movie.getProduction())
                .website(movie.getWebsite())
                .build();
    }

    public static Movie toEntity(MovieDto dto) {
        if (dto == null) return null;

        Movie movie = new Movie();
        if (dto.getMovieId() != null) {
            movie.setId(new java.math.BigInteger(dto.getMovieId()));
        }
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setBoxOfficeCollectionUsd(dto.getBoxOfficeCollectionUsd());
        movie.setRated(dto.getRated());
        movie.setReleased(dto.getReleased());
        movie.setRuntime(dto.getRuntime());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setWriter(dto.getWriter());
        movie.setActors(dto.getActors());
        movie.setPlot(dto.getPlot());
        movie.setLanguage(dto.getLanguage());
        movie.setCountry(dto.getCountry());
        movie.setAwards(dto.getAwards());
        movie.setPoster(dto.getPoster());
        movie.setMetaScore(dto.getMetaScore());
        movie.setImdbRating(dto.getImdbRating());
        movie.setImdbVotes(dto.getImdbVotes());
        movie.setImdbId(dto.getImdbId());
        movie.setType(dto.getType());
        movie.setDvd(dto.getDvd());
        movie.setProduction(dto.getProduction());
        movie.setWebsite(dto.getWebsite());

        return movie;
    }
}
