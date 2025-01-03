package com.project.Movie.Collections.domain.serializerAndDeserealizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.project.Movie.Collections.domain.dto.GenreDto;
import java.io.IOException;
import java.util.Optional;

public class GenreDtoDeserializer extends JsonDeserializer<GenreDto> {

    @Override
    public GenreDto deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = Optional.ofNullable(node.get("name"))
                .map(JsonNode::asText)
                .orElseThrow(() -> new RuntimeException("The 'name' field is required in 'genre'."));

        return GenreDto.builder().name(name).build();
    }

}

