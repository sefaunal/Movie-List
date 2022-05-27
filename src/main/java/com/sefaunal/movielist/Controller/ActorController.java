package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.Actor;
import com.sefaunal.movielist.Service.ActorService;
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
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("/user/home/actor")
    public String addActor(Model model){
        model.addAttribute("actor", new Actor());
        return "AddActor";
    }


    @PostMapping("/user/home/actor/add")
    public RedirectView createActor(@ModelAttribute Actor actor){
        actorService.createActor(actor);
        return new RedirectView("/user/home");
    }


    @GetMapping("/admin/panel/actors/update")
    public ModelAndView detailActor(@RequestParam int id, Model model){
        Actor actor = actorService.findById(id);
        model.addAttribute("actor", actor);
        return new ModelAndView("UpdateActor");
    }

    @PostMapping("/admin/panel/actors/update/apply")
    public RedirectView updateActor(@ModelAttribute Actor actor){
        actorService.createActor(actor);
        return new RedirectView("/admin/panel/actors");
    }


    @GetMapping("/admin/panel/actors/delete")
    public RedirectView deleteActor(int id){
        actorService.deleteById(id);
        return new RedirectView("/admin/panel/actors");
    }
}
