package com.sefaunal.movielist.Controller;

import com.sefaunal.movielist.Model.User;
import com.sefaunal.movielist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("registerUser")
    public ModelAndView createAccount(@RequestParam String name,
                                      @RequestParam String mail,
                                      @RequestParam String password,
                                      @RequestParam("userImage")MultipartFile userImage){
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setRole("USER");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        try {
            user.setUserImage(Base64.getEncoder().encodeToString(userImage.getBytes()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        user.setCreationDate(new Date());

        boolean x = userService.createUser(user);

        if (x == true)
            return new ModelAndView("/addaccountsuccess");
        else
            return new ModelAndView("/addaccountfail");
    }
}
