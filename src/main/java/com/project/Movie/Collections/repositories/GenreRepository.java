package com.project.Movie.Collections.repositories;

import com.project.Movie.Collections.domain.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<GenreEntity, String>, PagingAndSortingRepository<GenreEntity, String> {
    Optional<GenreEntity> findByName(String name);
}
