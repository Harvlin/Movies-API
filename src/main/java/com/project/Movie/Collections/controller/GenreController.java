package com.project.Movie.Collections.controller;

import com.project.Movie.Collections.domain.dto.GenreDto;
import com.project.Movie.Collections.domain.entities.GenreEntity;
import com.project.Movie.Collections.mappers.Mapper;
import com.project.Movie.Collections.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;
    private final Mapper<GenreEntity, GenreDto> genreMapper;

    @Autowired
    public GenreController(GenreService genreService, Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreService.save(genreEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreMapper.mapTo(savedGenreEntity));
    }

    @GetMapping
    public ResponseEntity<Page<GenreDto>> listGenres(Pageable pageable) {
        Page<GenreDto> genreDtos = genreService.findAll(pageable).map(genreMapper::mapTo);
        return ResponseEntity.ok(genreDtos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable("name") String name) {
        return genreService.findByName(name)
                .map(genreEntity -> ResponseEntity.ok(genreMapper.mapTo(genreEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{name}")
    public ResponseEntity<GenreDto> fullUpdateGenre (@PathVariable("name") String name, @RequestBody GenreDto genreDto) {
        return genreService.findByName(name)
                .map(existingGenre -> {
                    GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
                    GenreEntity savedGenreEntity = genreService.save(genreEntity);
                    return ResponseEntity.ok(genreMapper.mapTo(savedGenreEntity));
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{name}")
    public ResponseEntity<GenreDto> partialUpdateGenre(@PathVariable("name") String name, @RequestBody GenreDto genreDto) {
        return genreService.findByName(name)
                .map(existingGenre -> {
                    GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
                    GenreEntity updatedGenreEntity= genreService.partialUpdate(name, genreEntity);
                    return ResponseEntity.ok(genreMapper.mapTo(updatedGenreEntity));
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("name") String name) {
        if (!genreService.findByName(name).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        genreService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
