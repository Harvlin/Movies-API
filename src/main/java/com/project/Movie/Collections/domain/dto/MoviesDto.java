package com.project.Movie.Collections.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    private DirectorDto director;
}
