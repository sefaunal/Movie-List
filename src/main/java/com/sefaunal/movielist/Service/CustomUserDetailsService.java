package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.CustomUserDetails;
import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByMail(username);
        if (user == null){
            throw new UsernameNotFoundException("User Not Found!");
        }

        return new CustomUserDetails(user);
    }
}
