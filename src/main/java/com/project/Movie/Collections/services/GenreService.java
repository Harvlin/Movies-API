package com.project.Movie.Collections.services;

import com.project.Movie.Collections.domain.entities.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface GenreService {
    GenreEntity save(GenreEntity genre);

    GenreEntity partialUpdate(String name, GenreEntity genre);

    Page<GenreEntity> findAll(Pageable pageable);

    Optional<GenreEntity> findByName(String name);

    void deleteByName(String name);
}
