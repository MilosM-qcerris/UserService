package com.project.users.controller;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    private boolean checkPassword(String encodedPassword, String encodedConfirmPassword) {
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(user.getPassword(), user.getConfirmPassword());
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()){ return "registration"; }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        BCryptPasswordEncoder passwordMatcher = new BCryptPasswordEncoder();
        String encodedConfirmPassword = passwordMatcher.encode(user.getConfirmPassword());
        if (user.getPassword().equals(user.getConfirmPassword())){
            user.setPassword(encodedPassword);
            user.setConfirmPassword(encodedConfirmPassword);
            userRepository.save(user);
            System.out.println("I have entered in statement");
            return "success";
        }
        return "registration";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

}
