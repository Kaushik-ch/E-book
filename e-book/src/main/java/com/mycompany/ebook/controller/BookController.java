package com.mycompany.ebook.controller;


import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.User;
import com.mycompany.ebook.repository.BookRepository;
import com.mycompany.ebook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titleContains", required = false) String titleContains,
            @RequestParam(value = "authorContains", required = false) String authorContains,
            @RequestParam(value = "descriptionContains", required = false) String descriptionContains,
            @RequestParam(value= "all", required = false) String all
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

        List<Book> books = null;

        if (id != null) {
            books = bookRepository.findBookById(id);
        } else if (isbn != null) {
            books = bookRepository.findByIsbn(isbn);
        } else if (all != null)  {
            books = bookRepository.findAll();
        } else if (title != null && author != null ) {
            books = bookRepository.findByTitleAndAuthor(title, author);
        } else if (title != null ) {
            books = bookRepository.findByTitle(title);
        } else if (author != null ) {
            books = bookRepository.findByAuthor(author);
        } else if (price != null ) {
            books = bookRepository.findByPrice(price);
        } else if (minPrice != null && maxPrice != null ) {
            books = bookRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (titleContains != null) {
            books = bookRepository.findByTitleContains(titleContains);
        } else if (authorContains != null) {
            books = bookRepository.findByAuthorContains(authorContains);
        } else if (descriptionContains != null) {
            books = bookRepository.findByDescriptionContains(descriptionContains);
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("/")
    public ResponseEntity<?> createBook(@RequestBody Book book) {

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

        try {
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving book");
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {

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

        try {
            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating book");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> createBook(@PathVariable("id") Long id) {
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

        try {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ConfigDataResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
