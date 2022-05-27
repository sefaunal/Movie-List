package com.sefaunal.movielist.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieID;

    @Column(columnDefinition = "VARCHAR(150) NOT NULL", unique = true)
    private String movieName;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String movieDescription;

    @Temporal(TemporalType.DATE)
    private Date movieReleaseDate;

    @Column(columnDefinition = "VARCHAR(50)")
    private String movieRating;

    private String movieTrailer;

    @Lob
    private String movieImage;


    @ManyToMany(fetch = FetchType.EAGER,
    cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
    targetEntity = Language.class)
    @JoinTable(name = "MovieLanguages",
        foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Language>languageList;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
    targetEntity = Actor.class)
    @JoinTable(name = "MovieActors",
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Actor>actorList;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
    targetEntity = Genre.class)
    @JoinTable(name = "MovieGenres",
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Genre>genreList;
}
