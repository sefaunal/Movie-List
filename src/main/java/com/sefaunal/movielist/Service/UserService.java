package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean createUser(User user){
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public User findByMail(String mail){
        return userRepository.findByMail(mail);
    }

    public User findByRole(String role){
        return userRepository.findByRole(role);
    }

    public User findById(int id){
        return userRepository.findById(id).get();
    }
}
