package com.project.Movie.Collections.services.impl;

import com.project.Movie.Collections.domain.entities.GenreEntity;
import com.project.Movie.Collections.repositories.GenreRepository;
import com.project.Movie.Collections.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreEntity save(GenreEntity genre) {
        return genreRepository.save(genre);
    }

    @Override
    public GenreEntity partialUpdate(String name, GenreEntity genre) {
        GenreEntity existingGenre = genreRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Genre Not Found"));

        Optional.ofNullable(genre.getName()).ifPresent(existingGenre::setName);
        return genreRepository.save(existingGenre);
    }

    @Override
    public Page<GenreEntity> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Optional<GenreEntity> findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        GenreEntity genre = genreRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Genre Not Found with name: " + name));
        genreRepository.delete(genre);
    }
}
