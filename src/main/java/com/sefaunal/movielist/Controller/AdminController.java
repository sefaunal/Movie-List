package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.*;
import com.sefaunal.movielist.Repository.*;
import com.sefaunal.movielist.Service.CommentService;
import com.sefaunal.movielist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @GetMapping("/panel")
    public ModelAndView adminPanel(Model model){
        return new ModelAndView("AdminPanel");
    }

    @GetMapping("/panel/movies")
    public ModelAndView adminPanelMovies(Model model){
        List<Movie>movieList=movieRepository.findAll();
        model.addAttribute("movies",movieList);
        return new ModelAndView("AdminMovies");
    }

    @GetMapping("/panel/actors")
    public ModelAndView adminPanelActors(Model model){
        List<Actor>actorList=actorRepository.findAll();
        model.addAttribute("actors",actorList);
        return new ModelAndView("AdminActors");
    }

    @GetMapping("/panel/genres")
    public ModelAndView adminPanelGenres(Model model){
        List<Genre>genreList=genreRepository.findAll();
        model.addAttribute("genres",genreList);
        return new ModelAndView("AdminGenres");
    }

    @GetMapping("/panel/languages")
    public ModelAndView adminPanelLanguages(Model model){
        List<Language>languageList=languageRepository.findAll();
        model.addAttribute("languages",languageList);
        return new ModelAndView("AdminLanguages");
    }

    @GetMapping("/panel/users")
    public ModelAndView adminPanelUsers(Model model){
        List<User>userList=userRepository.findAll();
        model.addAttribute("users",userList);
        return new ModelAndView("AdminUsers");
    }

    @GetMapping("/panel/users/details")
    public ModelAndView adminUserDetails(@RequestParam int userID, Model model){
        User user = userService.findById(userID);
        List<Comment>commentList=commentRepository.findByUserID(userID);
        model.addAttribute("user",user);
        model.addAttribute("comments",commentList);
        return new ModelAndView("UserDetails");
    }

    @GetMapping("/panel/users/details/comment/delete")
    public RedirectView adminCommentDelete(@RequestParam long commentID){
        int UserID = commentService.findByID(commentID).getUser().getUserID();
        commentRepository.deleteById(commentID);
        return new RedirectView("/admin/panel/users/details?userID=" + UserID);
    }
}
