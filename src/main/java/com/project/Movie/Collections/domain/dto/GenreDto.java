package com.project.Movie.Collections.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Movie.Collections.domain.serializerAndDeserealizer.GenreDtoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = GenreDtoDeserializer.class)
public class GenreDto {

    private String names;

    @JsonIgnore
    private Set<MoviesDto> movies;
}
