package com.sefaunal.movielist;

import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class MovielistApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(MovielistApplication.class, args);

		UserService userService = applicationContext.getBean(UserService.class);

		User admin = userService.findByRole("ADMIN");
		if (admin == null){
			User user = new User();
			user.setName("ADMIN");
			user.setMail("sefa@admin.com");
			user.setRole("ADMIN");
			user.setCreationDate(new Date());
			user.setUserImage(null);
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			userService.createUser(user);
		}
	}
}
