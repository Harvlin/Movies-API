package com.project.Movie.Collections.mappers.impl;

import com.project.Movie.Collections.domain.dto.MoviesDto;
import com.project.Movie.Collections.domain.entities.MoviesEntity;
import com.project.Movie.Collections.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MoviesMapperImpl implements Mapper<MoviesEntity, MoviesDto> {
    private ModelMapper modelMapper;

    public MoviesMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MoviesDto mapTo(MoviesEntity moviesEntity) {
        return modelMapper.map(moviesEntity, MoviesDto.class);
    }

    @Override
    public MoviesEntity mapFrom(MoviesDto moviesDto) {
        return modelMapper.map(moviesDto, MoviesEntity.class);
    }
}
