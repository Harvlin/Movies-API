package com.project.Movie.Collections.repositories;

import com.project.Movie.Collections.domain.entities.MoviesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<MoviesEntity, String>, PagingAndSortingRepository<MoviesEntity, String> {
    Optional<MoviesEntity> findByTitle (String title);
}
