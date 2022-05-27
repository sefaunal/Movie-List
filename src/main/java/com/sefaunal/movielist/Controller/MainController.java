package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.Movie;
import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Repository.MovieRepository;
import com.sefaunal.movielist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public RedirectView redirect(Principal principal, Model model) {
        if (principal == null) {
            return new RedirectView("/guest/home");
        } else {
            User user = userRepository.findByMail(principal.getName());
            return new RedirectView("/user/home");
        }
    }

    @GetMapping("/login")
    public String loginPage(HttpServletResponse httpServletResponse, Principal principal){
        if (principal != null){
            try {
                httpServletResponse.sendRedirect("/user/home");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return "LoginPage";
    }

    @GetMapping("/guest/home")
    public ModelAndView homePage(Model model){
        User user = null;
        List<Movie>movieList=movieRepository.findAll();
        model.addAttribute("Movie", movieList);
        return new ModelAndView("HomePage");
    }
}
