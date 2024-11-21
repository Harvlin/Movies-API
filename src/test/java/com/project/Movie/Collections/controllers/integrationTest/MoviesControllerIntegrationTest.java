package com.project.Movie.Collections.controllers.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Movie.Collections.TestDataUtil;
import com.project.Movie.Collections.domain.dto.DirectorDto;
import com.project.Movie.Collections.domain.dto.GenreDto;
import com.project.Movie.Collections.domain.dto.MoviesDto;
import com.project.Movie.Collections.services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class MoviesControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private MovieService movieService;

    public MoviesControllerIntegrationTest(ObjectMapper objectMapper, MovieService movieService, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.movieService = movieService;
    }

    @Test
    public void TestThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {
        DirectorDto directorDto = TestDataUtil.createDirectorDto();
        GenreDto genreDto = TestDataUtil.createGenreDto();
        MoviesDto moviesDto = TestDataUtil.createTestMovieDto(genreDto, directorDto);

        String movieJson = objectMapper.writeValueAsString(moviesDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(movieJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}