package com.mycompany.ebook.controller;

import com.mycompany.ebook.entity.User;
import com.mycompany.ebook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController // Marks the class a rest controller
@RequestMapping("/api/user") // Requests made to /api/auth/anything will be handles by this class
public class UserController {

    // Injecting Dependencies
    @Autowired private UserRepo userRepo;

    // Defining the function to handle the GET route to fetch user information of the authenticated user
    @GetMapping("/info")
    public User getUserDetails(){
        // Retrieve email from the Security Context
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Fetch and return user details

        User userAuth = userRepo.findByEmailOrderByIdDesc(email).stream().findFirst().get();

        return userAuth;
    }


}
