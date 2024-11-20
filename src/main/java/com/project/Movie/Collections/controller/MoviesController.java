package com.project.Movie.Collections.controller;

import com.project.Movie.Collections.domain.dto.MoviesDto;
import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.domain.entities.GenreEntity;
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
    private final MovieService movieService;
    private final Mapper<MoviesEntity, MoviesDto> movieMapper;

    @Autowired
    public MoviesController(MovieService movieService, Mapper<MoviesEntity, MoviesDto> movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping()
    public ResponseEntity<MoviesEntity> createMovie(@RequestBody MoviesDto moviesDto) {
        MoviesEntity movie = new MoviesEntity();
        movie.setTitle(moviesDto.getTitle());
        movie.setDescription(moviesDto.getDescription());

        GenreEntity genre = new GenreEntity();
        genre.setName(moviesDto.getGenre().getNames());
        movie.setGenre(genre);

        DirectorEntity director = new DirectorEntity();
        director.setName(moviesDto.getDirector().getName());
        director.setAge(moviesDto.getDirector().getAge());
        movie.setDirector(director);

        MoviesEntity savedMovie = movieService.save(movie);
        return ResponseEntity.ok(savedMovie);
    }


    @GetMapping
    public ResponseEntity<Page<MoviesDto>> listMovies(Pageable pageable) {
        Page<MoviesDto> movieDtos = movieService.findAll(pageable).map(movieMapper::mapTo);
        return ResponseEntity.ok(movieDtos);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MoviesDto> getMovie(@PathVariable("title") String title) {
        return movieService.findByTitle(title)
                .map(movieEntity -> ResponseEntity.ok(movieMapper.mapTo(movieEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{title}")
    public ResponseEntity<MoviesDto> fullUpdateMovie(@PathVariable("title") String title, @RequestBody MoviesDto movieDto) {
        return movieService.findByTitle(title)
                .map(existingDirector -> {
                    MoviesEntity movieEntity = movieMapper.mapFrom(movieDto);
                    MoviesEntity savedMovieEntity = movieService.save(movieEntity);
                    return ResponseEntity.ok(movieMapper.mapTo(savedMovieEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{title}")
    public ResponseEntity<MoviesDto> partialUpdateMovie(@PathVariable("title") String title, @RequestBody MoviesDto movieDto) {
        return movieService.findByTitle(title)
                .map(existingDirector -> {
                    MoviesEntity movieEntity = movieMapper.mapFrom(movieDto);
                    MoviesEntity updatedMovieEntity = movieService.partialUpdate(title, movieEntity);
                    return ResponseEntity.ok(movieMapper.mapTo(updatedMovieEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("title") String title) {
        if (!movieService.findByTitle(title).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        movieService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
