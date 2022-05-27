package com.sefaunal.movielist.Repository;

import com.sefaunal.movielist.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByMail(String name);

    User findByRole(String role);
}
