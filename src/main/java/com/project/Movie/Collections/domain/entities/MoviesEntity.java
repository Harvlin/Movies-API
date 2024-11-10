package com.project.Movie.Collections.domain.entities;

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
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "genre")
    private GenreEntity genre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "director")
    private DirectorEntity director;
}
