package com.mycompany.ebook.repository;

import com.mycompany.ebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Defines a repository which provides an API or a list of helpful functions
// that helps us to work with the User entity
public interface UserRepo extends JpaRepository<User, Long> {
    // Defines a custom method to find a User using the email attribute
    public Optional<User> findByEmailOrderByIdDesc(String email);

    //public Optional<User> findByStudentLogin(String studentLogin);
}
