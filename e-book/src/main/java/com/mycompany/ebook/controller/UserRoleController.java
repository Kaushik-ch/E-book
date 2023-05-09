package com.mycompany.ebook.controller;


import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.User;
import com.mycompany.ebook.entity.UserRole;
import com.mycompany.ebook.repository.BookRepository;
import com.mycompany.ebook.repository.UserRepo;
import com.mycompany.ebook.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/search")
    public ResponseEntity<UserRole> getUserRole(
            @RequestParam(value = "userEmail", required = true) String userEmail
    ) {

        //JWT authentication
        try {
            // Retrieve email from the Security Context
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // Fetch and return user details
            User loggedUser = userRepo.findByEmailOrderByIdDesc(email).get();
            if (loggedUser.getId() == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserRole userRole =  userRoleRepository.findUserRoleByUserEmail(userEmail);
        return ResponseEntity.ok(userRole);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUserRole(@RequestBody UserRole userRole) {

        //JWT authentication
        String email = "";
        try {
            // Retrieve email from the Security Context
            email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // Fetch and return user details
            User loggedUser = userRepo.findByEmailOrderByIdDesc(email).get();
            if (loggedUser.getId() == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        //Authorize only Admins can access this resource
        UserRole authUserRole =  userRoleRepository.findUserRoleByUserEmail(email);
        System.out.println(authUserRole.getRole().getRole());
        if(!authUserRole.getRole().getRole().equals("Admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sorry. You don't have credentials to access the resource");
        }

        //Input validations
        if(userRole.getUserEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide user email");
        }
        if(userRole.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide role");
        }

        try {
            UserRole savedUserRole = userRoleRepository.save(userRole);
            return ResponseEntity.ok(savedUserRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user role");
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUserRole(@RequestBody UserRole userRole) {

        //JWT authentication
        String email = "";
        try {
            // Retrieve email from the Security Context
            email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // Fetch and return user details
            User loggedUser = userRepo.findByEmailOrderByIdDesc(email).get();
            if (loggedUser.getId() == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        //Authorize only Admins to add new user roles
        /*UserRole authUserRole =  userRoleRepository.findUserRoleByUserEmail(email);
        System.out.println(authUserRole.getRole().getRole());
        if(!authUserRole.getRole().getRole().equals("Admin")) {
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sorry. You don't have credentials to access the resource");
        }*/

        //Input validations
        if(userRole.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide user role id");
        }
        if(userRole.getUserEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide user email");
        }
        if(userRole.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide role");
        }

        try {
            UserRole savedUserRole = userRoleRepository.save(userRole);
            return ResponseEntity.ok(savedUserRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user role");
        }
    }
}
