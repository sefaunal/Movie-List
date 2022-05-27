package com.sefaunal.movielist.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorID;

    @Column(columnDefinition = "VARCHAR(150) NOT NULL", name = "actor", unique = true)
    private String actorName;

    private String actorGender;

}
