package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.Language;
import com.sefaunal.movielist.Service.LanguageService;
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
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping("/user/home/language")
    public String addLanguage(Model model){
        model.addAttribute("language", new Language());
        return "AddLanguage";
    }

    @PostMapping("/user/home/language/add")
    public RedirectView createLanguage(@ModelAttribute Language language){
        languageService.createLanguage(language);
        return new RedirectView("/user/home");
    }


    @GetMapping("/admin/panel/languages/update")
    public ModelAndView detailLanguage(@RequestParam int id, Model model){
        Language language = languageService.findById(id);
        model.addAttribute("language", language);
        return new ModelAndView("UpdateLanguage");
    }

    @PostMapping("/admin/panel/languages/update/apply")
    public RedirectView updateLanguage(@ModelAttribute Language language){
        languageService.createLanguage(language);
        return new RedirectView("/admin/panel/languages");
    }


    @GetMapping("/admin/panel/languages/delete")
    public RedirectView deleteLanguage(int id){
        languageService.deleteById(id);
        return new RedirectView("/admin/panel/languages");
    }
}
