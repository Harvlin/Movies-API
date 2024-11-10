package com.project.Movie.Collections.services;

import com.project.Movie.Collections.domain.entities.MoviesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MoviesEntity save(MoviesEntity movie);

    Page<MoviesEntity> findAll(Pageable pageable);

    Optional<MoviesEntity> findByTitle(String title);

    List<MoviesEntity> findByGenre(String genreName);

    List<MoviesEntity> findByDirector(String directorName);

    MoviesEntity partialUpdate(String title, MoviesEntity movie);

    void deleteByTitle(String title);
}
