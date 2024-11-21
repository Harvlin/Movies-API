package com.project.Movie.Collections.services.impl;

import com.project.Movie.Collections.domain.entities.DirectorEntity;
import com.project.Movie.Collections.domain.entities.GenreEntity;
import com.project.Movie.Collections.domain.entities.MoviesEntity;
import com.project.Movie.Collections.repositories.MovieRepository;
import com.project.Movie.Collections.services.DirectorService;
import com.project.Movie.Collections.services.GenreService;
import com.project.Movie.Collections.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorService directorService;
    private final GenreService genreService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, DirectorService directorService, GenreService genreService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public MoviesEntity save(MoviesEntity movie) {
        GenreEntity genre = genreService.findByName(movie.getGenre().getName())
                .orElseGet(() -> genreService.save(movie.getGenre()));

        DirectorEntity director = directorService.findByName(movie.getDirector().getName())
                .orElseGet(() -> directorService.save(movie.getDirector()));

        movie.setGenre(genre);
        movie.setDirector(director);

        return movieRepository.save(movie);
    }

    @Override
    public Page<MoviesEntity> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Optional<MoviesEntity> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public Set<MoviesEntity> findByGenre(String genreName) {
        GenreEntity genre = genreService.findByName(genreName)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found."));
        return genre.getMovies();
    }

    @Override
    public List<MoviesEntity> findByDirector(String directorName) {
        DirectorEntity director = directorService.findByName(directorName)
                .orElseThrow(() -> new IllegalArgumentException("Director not found."));
        return director.getMovies();
    }

    @Override
    public MoviesEntity partialUpdate(String title, MoviesEntity movie) {
        MoviesEntity existingMovie = movieRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Movie Not Found"));

        Optional.ofNullable(movie.getDescription()).ifPresent(existingMovie::setDescription);
        Optional.ofNullable(movie.getGenre()).ifPresent(genre -> {
            GenreEntity updatedGenre = genreService.save(genre);
            existingMovie.setGenre(updatedGenre);
        });
        Optional.ofNullable(movie.getDirector()).ifPresent(director -> {
            DirectorEntity updatedDirector = directorService.save(director);
            existingMovie.setDirector(updatedDirector);
        });

        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteByTitle(String title) {
        MoviesEntity movie = movieRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Movie Not Found with title: " + title));
        movieRepository.delete(movie);
    }

    @Override
    public boolean isExist(Long id) {
        return movieRepository.existsById(id);
    }
}
