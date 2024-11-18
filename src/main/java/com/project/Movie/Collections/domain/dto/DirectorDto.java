package com.project.Movie.Collections.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.Movie.Collections.domain.serializerAndDeserealizer.DirectorDtoSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize(using = DirectorDtoSerializer.class)
public class DirectorDto {

    private Long id;
    private String name;
    private Integer age;

    @JsonIgnore // Optional: Exclude moviesTitles from serialization
    private List<MoviesDto> moviesTitles;
}
