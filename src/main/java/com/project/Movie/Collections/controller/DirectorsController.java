package com.project.Movie.Collections.controller;

import com.project.Movie.Collections.domain.dto.DirectorDto;
import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.mappers.Mapper;
import com.project.Movie.Collections.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/directors")
public class DirectorsController {

    private final DirectorService directorService;
    private final Mapper<DirectorEntity, DirectorDto> directorMapper;

    @Autowired
    public DirectorsController(DirectorService directorService, Mapper<DirectorEntity, DirectorDto> directorMapper) {
        this.directorService = directorService;
        this.directorMapper = directorMapper;
    }

    @PostMapping
    public ResponseEntity<DirectorDto> createDirector(@RequestBody DirectorDto directorDto) {
        DirectorEntity directorEntity = directorMapper.mapFrom(directorDto);
        DirectorEntity savedDirectorEntity = directorService.save(directorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(directorMapper.mapTo(savedDirectorEntity));
    }

    @GetMapping
    public ResponseEntity<Page<DirectorDto>> listDirectors(Pageable pageable) {
        Page<DirectorDto> directorDtos = directorService.findAll(pageable).map(directorMapper::mapTo);
        return ResponseEntity.ok(directorDtos);
    }


    @GetMapping("/{name}")
    public ResponseEntity<DirectorDto> getDirector(@PathVariable("name") String name) {
        return directorService.findByName(name)
                .map(directorEntity -> ResponseEntity.ok(directorMapper.mapTo(directorEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{name}")
    public ResponseEntity<DirectorDto> fullUpdateDirector(@PathVariable("name") String name, @RequestBody DirectorDto directorDto) {
        return directorService.findByName(name)
                .map(existingDirector -> {
                    DirectorEntity directorEntity = directorMapper.mapFrom(directorDto);
                    DirectorEntity savedDirectorEntity = directorService.save(directorEntity);
                    return ResponseEntity.ok(directorMapper.mapTo(savedDirectorEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{name}")
    public ResponseEntity<DirectorDto> partialUpdateDirector(@PathVariable("name") String name, @RequestBody DirectorDto directorDto) {
        return directorService.findByName(name)
                .map(existingDirector -> {
                    DirectorEntity directorEntity = directorMapper.mapFrom(directorDto);
                    DirectorEntity updatedDirectorEntity = directorService.partialUpdate(name, directorEntity);
                    return ResponseEntity.ok(directorMapper.mapTo(updatedDirectorEntity));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("name") String name) {
        if (!directorService.findByName(name).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        directorService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
