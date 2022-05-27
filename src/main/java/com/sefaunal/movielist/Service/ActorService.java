package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.Actor;
import com.sefaunal.movielist.Repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public Actor findById(int id){
        return actorRepository.findById(id).get();
    }

    public void createActor(Actor actor){
        try {
            actorRepository.save(actor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(int id){
        actorRepository.deleteById(id);
    }
}
