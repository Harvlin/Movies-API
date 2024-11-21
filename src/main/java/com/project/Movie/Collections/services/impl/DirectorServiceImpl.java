package com.project.Movie.Collections.services.impl;

import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.repositories.DirectorRepository;
import com.project.Movie.Collections.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public DirectorEntity save(DirectorEntity director) {
        return directorRepository.save(director);
    }

    @Override
    public Page<DirectorEntity> findAll(Pageable pageable) {
        return directorRepository.findAll(pageable);
    }

    @Override
    public Optional<DirectorEntity> findByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public DirectorEntity partialUpdate(String name, DirectorEntity director) {
        return directorRepository.findByName(name).map(existingDirector -> {
            Optional.ofNullable(director.getName()).ifPresent(existingDirector::setName);
            Optional.ofNullable(director.getAge()).ifPresent(existingDirector::setAge);
            return directorRepository.save(existingDirector);
        }).orElseThrow(() -> new RuntimeException("Director Not Found"));
    }

    @Override
    public void delete(String name) {
        directorRepository.findByName(name)
                .ifPresentOrElse(directorRepository::delete,
                        () -> { throw new RuntimeException("Director Not Found with name: " + name); });
    }
}
