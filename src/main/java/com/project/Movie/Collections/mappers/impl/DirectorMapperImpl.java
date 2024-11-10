package com.project.Movie.Collections.mappers.impl;

import com.project.Movie.Collections.domain.dto.DirectorDto;
import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapperImpl implements Mapper<DirectorEntity, DirectorDto> {
    private ModelMapper modelMapper;

    public DirectorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DirectorDto mapTo(DirectorEntity directorEntity) {
        return modelMapper.map(directorEntity, DirectorDto.class);
    }

    @Override
    public DirectorEntity mapFrom(DirectorDto directorDto) {
        return modelMapper.map(directorDto, DirectorEntity.class);
    }
}
