package com.project.Movie.Collections.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviesDto {

    private String title;

    private String description;

    private GenreDto genre;

    @JsonIgnoreProperties(value = {"movies"})
    private DirectorDto director;
}
