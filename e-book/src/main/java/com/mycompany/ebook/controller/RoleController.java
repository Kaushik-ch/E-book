package com.mycompany.ebook.controller;

import com.mycompany.ebook.entity.Role;
import com.mycompany.ebook.entity.User;
import com.mycompany.ebook.repository.RoleRepository;
import com.mycompany.ebook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {

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

        //Return all roles
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
