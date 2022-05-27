package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.Actor;
import com.sefaunal.movielist.Model.Genre;
import com.sefaunal.movielist.Model.Language;
import com.sefaunal.movielist.Model.Movie;
import com.sefaunal.movielist.Repository.ActorRepository;
import com.sefaunal.movielist.Repository.GenreRepository;
import com.sefaunal.movielist.Repository.LanguageRepository;
import com.sefaunal.movielist.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    LanguageRepository languageRepository;

    public Movie findById(long id){
        return movieRepository.findById(id).get();
    }

    public void createMovie(Movie movie, String[] actorList, String[] genreList, String[] languageList){

        try {
            List<Actor> actors = new ArrayList<>();
            List<Genre> genres = new ArrayList<>();
            List<Language> languages = new ArrayList<>();

            for (String actorID : actorList) {
                actors.add(actorRepository.findById(Integer.parseInt(actorID)).get());
            }
            for (String genreID : genreList) {
                genres.add(genreRepository.findById(Integer.parseInt(genreID)).get());
            }
            for (String languageID : languageList) {
                languages.add(languageRepository.findById(Integer.parseInt(languageID)).get());
            }
            movie.setActorList(actors);
            movie.setGenreList(genres);
            movie.setLanguageList(languages);

            movieRepository.save(movie);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteByID(long id){
        movieRepository.deleteById(id);
    }
}
