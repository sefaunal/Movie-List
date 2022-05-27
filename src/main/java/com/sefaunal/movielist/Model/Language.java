package com.sefaunal.movielist.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageID;

    @Column(columnDefinition = "VARCHAR(75) NOT NULL", name = "language", unique = true)
    private String languageName;
}
