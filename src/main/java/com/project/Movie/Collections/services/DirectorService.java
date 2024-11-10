package com.project.Movie.Collections.services;

import com.project.Movie.Collections.domain.entities.DirectorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DirectorService {
    DirectorEntity save(DirectorEntity director);

    Page<DirectorEntity> findAll(Pageable pageable);

    Optional<DirectorEntity> findByName(String name);

    boolean existsById(Long id);

    DirectorEntity partialUpdate(Long id, DirectorEntity director);

    void delete(Long id);
}
