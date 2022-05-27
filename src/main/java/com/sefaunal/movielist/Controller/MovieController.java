package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.*;
import com.sefaunal.movielist.Repository.*;
import com.sefaunal.movielist.Service.CommentService;
import com.sefaunal.movielist.Service.MovieService;
import com.sefaunal.movielist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @GetMapping("/user/home/movie/search")
    public ModelAndView movieSearch(@RequestParam String searchParam, Model model, Principal principal){
        List<Movie> searchMovie=movieRepository.Search(searchParam);
        model.addAttribute("movie",searchMovie);

        User user;
        if (principal != null){
            user = userService.findByMail(principal.getName());
        }else {
            user = null;
        }


        model.addAttribute("user", user);
        model.addAttribute("Movie",searchMovie);
        return new ModelAndView("HomePage");
    }

    @GetMapping("/user/home/movie")
    public ModelAndView addMovie(Model model) {
        List<Actor> actorList = actorRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Language> languageList = languageRepository.findAll();


        model.addAttribute("movie", new Movie());
        model.addAttribute("movieActors", actorList);
        model.addAttribute("movieLanguages", languageList);
        model.addAttribute("movieGenres", genreList);
        return new ModelAndView("AddMovie");
    }

    @PostMapping("/user/home/movie/add")
    public RedirectView createMovie(@RequestParam String movieName,
                                    @RequestParam String movieActors,
                                    @RequestParam String movieLanguages,
                                    @RequestParam String movieGenres,
                                    @RequestParam Date movieDate,
                                    @RequestParam float movieRate,
                                    @RequestParam String movieDescription,
                                    @RequestParam String movieTrailer,
                                    @RequestParam("movieImage") MultipartFile movieImage) {
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        movie.setMovieReleaseDate(movieDate);
        movie.setMovieRating(Float.toString(movieRate));
        movie.setMovieTrailer(movieTrailer);
        movie.setMovieDescription(movieDescription);
        try {
            movie.setMovieImage(Base64.getEncoder().encodeToString(movieImage.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] actorIDs = movieActors.split(",");
        String[] LanguageIDs = movieLanguages.split(",");
        String[] GenreIDs = movieGenres.split(",");

        movieService.createMovie(movie, actorIDs, GenreIDs, LanguageIDs);

        return new RedirectView("/user/home");
    }

    @GetMapping("/user/home/movie/details")
    public ModelAndView movieDetails(@RequestParam long movieID, Model model, Authentication authentication) {

        User user;
        if (authentication != null){
            user = userService.findByMail(authentication.getName());
        }
        else {
            user = null;
        }

        Movie movie = movieService.findById(movieID);
        List<Actor> actorList = new ArrayList<>();
        List<Genre> genreList = new ArrayList<>();
        List<Language> languageList = new ArrayList<>();
        List<Comment>commentList = commentRepository.findByMovieID(movieID);

        movie.getActorList().forEach(actor -> {
            actorList.add(actor);
        });
        movie.getGenreList().forEach(genre -> {
            genreList.add(genre);
        });
        movie.getLanguageList().forEach(language -> {
            languageList.add(language);
        });

        model.addAttribute("user", user);
        model.addAttribute("movie", movie);
        model.addAttribute("actors", actorList);
        model.addAttribute("genres", genreList);
        model.addAttribute("languages", languageList);
        model.addAttribute("comments", commentList);
        return new ModelAndView("MovieDetails");
    }

    @PostMapping("/user/home/movie/details/addComment")
    public RedirectView addComment(@RequestParam long id, @RequestParam String commentContext, Authentication authentication){
        User user = userService.findByMail(authentication.getName());
        Movie movie = movieService.findById(id);

        Comment comment = new Comment();
        comment.setCommentContext(commentContext);
        comment.setMovie(movie);
        comment.setUser(user);

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        comment.setCommentDate(localDateTime.format(dateTimeFormatter).toString());

        commentService.createComment(comment);

        return new RedirectView("/user/home/movie/details?movieID=" + movie.getMovieID());
    }

    @GetMapping("/admin/panel/movies/update")
    public ModelAndView detailMovie(@RequestParam long id, Model model){
        Movie movie = movieService.findById(id);
        List<Actor> actorList = actorRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Language> languageList = languageRepository.findAll();

        model.addAttribute("movieActors", actorList);
        model.addAttribute("movieLanguages", languageList);
        model.addAttribute("movieGenres", genreList);
        model.addAttribute("movie",movie);

        return new ModelAndView("UpdateMovie");
    }

    @PostMapping("/admin/panel/movies/update/apply")
    public RedirectView updateMovie(@ModelAttribute Movie movie,
                                    @RequestParam String movieActors,
                                    @RequestParam String movieLanguages,
                                    @RequestParam String movieGenres,
                                    @RequestParam("movieImageUpdate") MultipartFile movieImageUpdate
                                    ){

        String[] actorIDs = movieActors.split(",");
        String[] LanguageIDs = movieLanguages.split(",");
        String[] GenreIDs = movieGenres.split(",");

        try {
            movie.setMovieImage(Base64.getEncoder().encodeToString(movieImageUpdate.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        movieService.createMovie(movie, actorIDs, GenreIDs, LanguageIDs);

        return new RedirectView("/admin/panel/movies");

    }

    @GetMapping("/admin/panel/movies/delete")
    public RedirectView deleteMovie(long id){
        commentRepository.deleteByMovieID(id);
        movieService.deleteByID(id);
        return new RedirectView("/admin/panel/movies");
    }
}
