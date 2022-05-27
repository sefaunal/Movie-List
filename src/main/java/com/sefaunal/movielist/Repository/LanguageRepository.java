package com.sefaunal.movielist.Repository;

import com.sefaunal.movielist.Model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
