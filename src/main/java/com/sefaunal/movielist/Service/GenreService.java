package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.Genre;
import com.sefaunal.movielist.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public Genre findById(int id){
        return genreRepository.findById(id).get();
    }

    public void createGenre(Genre genre){
        try {
            genreRepository.save(genre);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteById(int id){
        genreRepository.deleteById(id);
    }
}
