package com.sefaunal.movielist.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreID;

    @Column(columnDefinition = "VARCHAR(75) NOT NULL", name = "genre", unique = true)
    private String genreName;
}
