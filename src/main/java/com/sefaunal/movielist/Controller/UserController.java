package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.Movie;
import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Repository.MovieRepository;
import com.sefaunal.movielist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/home")
    public ModelAndView home(Principal principal, Model model){
        User user = userService.findByMail(principal.getName());
        List<Movie> movieList = movieRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("Movie",movieList);

        return new ModelAndView("HomePage");
    }
}
