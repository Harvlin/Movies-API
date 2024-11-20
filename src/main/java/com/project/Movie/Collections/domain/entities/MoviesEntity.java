package com.project.Movie.Collections.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class MoviesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_name", referencedColumnName = "name", nullable = false)
    private GenreEntity genre;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id", nullable = false)
    private DirectorEntity director;
}

