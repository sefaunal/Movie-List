package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.Language;
import com.sefaunal.movielist.Repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public Language findById(int id){
        return languageRepository.findById(id).get();
    }

    public void createLanguage(Language language){
        try {
            languageRepository.save(language);
        }catch (Exception e){
            System.out.println();
        }
    }

    public void deleteById(int id) {
        languageRepository.deleteById(id);
    }
}
