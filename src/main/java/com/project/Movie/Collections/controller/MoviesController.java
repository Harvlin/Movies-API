package com.project.Movie.Collections.controller;

import com.project.Movie.Collections.domain.dto.DirectorDto;
import com.project.Movie.Collections.domain.dto.MoviesDto;
import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.domain.entities.MoviesEntity;
import com.project.Movie.Collections.mappers.Mapper;
import com.project.Movie.Collections.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/movies")
public class MoviesController {
    private final MovieService moviesService;
    private final Mapper<MoviesEntity, MoviesDto> moviesMapper;

    @Autowired
    public MoviesController(MovieService moviesService, Mapper<MoviesEntity, MoviesDto> moviesMapper) {
        this.moviesService = moviesService;
        this.moviesMapper = moviesMapper;
    }

    @PostMapping
    public ResponseEntity<MoviesDto> createMovie(@RequestBody MoviesDto movieDto) {
        MoviesEntity movieEntity = moviesMapper.mapFrom(movieDto);
        MoviesEntity savedMoviesEntity = moviesService.save(movieEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(moviesMapper.mapTo(savedMoviesEntity));
    }

    @GetMapping
    public ResponseEntity<Page<MoviesDto>> listMovies(Pageable pageable) {
        Page<MoviesDto> moviesDtos = moviesService.findAll(pageable).map(moviesMapper::mapTo);
        return ResponseEntity.ok(moviesDtos);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MoviesDto> getMovie(@PathVariable("title") String title) {
        return moviesService.findByTitle(title)
                .map(moviesEntity -> ResponseEntity.ok(moviesMapper.mapTo(moviesEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{title}")
    public ResponseEntity<MoviesDto> fullUpdateMovie(@PathVariable("title") String title, @RequestBody MoviesDto movieDto) {
        return moviesService.findByTitle(title)
                .map(existingDirector -> {
                    MoviesEntity movieEntity = moviesMapper.mapFrom(movieDto);
                    MoviesEntity savedMovieEntity = moviesService.save(movieEntity);
                    return ResponseEntity.ok(moviesMapper.mapTo(savedMovieEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{title}")
    public ResponseEntity<MoviesDto> partialUpdateMovie(@PathVariable("title") String title, @RequestBody MoviesDto movieDto) {
        return moviesService.findByTitle(title)
                .map(existingDirector -> {
                    MoviesEntity movieEntity = moviesMapper.mapFrom(movieDto);
                    MoviesEntity updatedMovieEntity = moviesService.partialUpdate(title, movieEntity);
                    return ResponseEntity.ok(moviesMapper.mapTo(updatedMovieEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("title") String title) {
        if (!moviesService.findByTitle(title).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        moviesService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
