package com.project.Movie.Collections;

import com.project.Movie.Collections.domain.dto.DirectorDto;
import com.project.Movie.Collections.domain.dto.GenreDto;
import com.project.Movie.Collections.domain.dto.MoviesDto;

public class TestDataUtil {
    private TestDataUtil() {}

    public static GenreDto createGenreDto() {
        return GenreDto.builder()
                .names("Sci-fi")
                .movies(null)
                .build();
    }

    public static DirectorDto createDirectorDto() {
        return DirectorDto.builder()
                .name("Christopher Nolan")
                .age(50)
                .moviesTitles(null)
                .build();
    }
    public static MoviesDto createTestMovieDto(final GenreDto genreDto, final DirectorDto directorDto) {
        return MoviesDto.builder()
                .title("Inception")
                .description("This is a sci-fi movie")
                .genre(genreDto)
                .director(directorDto)
                .build();
    }
}
