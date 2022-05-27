package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.Genre;
import com.sefaunal.movielist.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/user/home/genre")
    public String addGenre(Model model){
        model.addAttribute("genre", new Genre());
        return "AddGenre";
    }

    @PostMapping("/user/home/genre/add")
    public RedirectView createGenre(@ModelAttribute Genre genre){
        genreService.createGenre(genre);
        return new RedirectView("/user/home");
    }

    @GetMapping("/admin/panel/genres/update")
    public ModelAndView detailGenre(@RequestParam int id, Model model){
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return new ModelAndView("UpdateGenre");
    }

    @PostMapping("/admin/panel/genres/update/apply")
    public RedirectView updateGenre(@ModelAttribute Genre genre){
        genreService.createGenre(genre);
        return new RedirectView("/admin/panel/genres");
    }

    @GetMapping("/admin/panel/genres/delete")
    public RedirectView deleteGenre(int id){
        genreService.deleteById(id);
        return new RedirectView("/admin/panel/genres");
    }
}
