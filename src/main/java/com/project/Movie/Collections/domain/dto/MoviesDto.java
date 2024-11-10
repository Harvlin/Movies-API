package com.project.Movie.Collections.domain.dto;

import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.domain.entities.GenreEntity;
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

    private GenreEntity genre;

    private DirectorEntity director;
}
