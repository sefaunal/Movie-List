package com.sefaunal.movielist.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String name;
    private String password;

    @Column(columnDefinition = "VARCHAR(175) NOT NULL", name = "mail", unique = true)
    private String mail;

    private String role;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Lob
    private String userImage;
}
