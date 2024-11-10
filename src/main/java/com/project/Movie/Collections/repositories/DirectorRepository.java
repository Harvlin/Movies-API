package com.project.Movie.Collections.repositories;

import com.project.Movie.Collections.domain.entities.DirectorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<DirectorEntity, Long>, PagingAndSortingRepository<DirectorEntity, Long> {
    Optional<DirectorEntity> findByName(String name);
}
