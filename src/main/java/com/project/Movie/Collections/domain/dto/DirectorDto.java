package com.project.Movie.Collections.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorDto {

    private Long id;

    private String name;

    private Integer age;

    private List<MoviesDto> movies;
}
