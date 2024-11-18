package com.project.Movie.Collections.domain.serializerAndDeserealizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.Movie.Collections.domain.dto.DirectorDto;

import java.io.IOException;

public class DirectorDtoSerializer extends JsonSerializer<DirectorDto> {

    @Override
    public void serialize(DirectorDto directorDto, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("id", directorDto.getId());
        generator.writeStringField("name", directorDto.getName());
        generator.writeNumberField("age", directorDto.getAge());
        generator.writeEndObject();
    }
}



