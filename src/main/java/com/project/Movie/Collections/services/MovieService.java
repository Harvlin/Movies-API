package com.project.Movie.Collections.services;

import com.project.Movie.Collections.domain.entities.MoviesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieService {
    MoviesEntity save(MoviesEntity movie);

    Page<MoviesEntity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"director", "genre"})
    Optional<MoviesEntity> findByTitle(String title);

    Set<MoviesEntity> findByGenre(String genreName);

    List<MoviesEntity> findByDirector(String directorName);

    MoviesEntity partialUpdate(String title, MoviesEntity movie);

    void deleteByTitle(String title);
}
