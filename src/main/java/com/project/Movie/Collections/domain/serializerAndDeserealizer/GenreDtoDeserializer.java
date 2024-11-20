package com.project.Movie.Collections.domain.serializerAndDeserealizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.project.Movie.Collections.domain.dto.GenreDto;

import java.io.IOException;

public class GenreDtoDeserializer extends JsonDeserializer<GenreDto> {

    @Override
    public GenreDto deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.has("name") ? node.get("name").asText() : null;

        if (name == null) {
            throw new IllegalArgumentException("The 'name' field in 'genre' is required.");
        }

        return GenreDto.builder().names(name).build();
    }
}
