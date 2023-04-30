package com.mycompany.ebook.controller;

import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.BookOrderDetail;
import com.mycompany.ebook.entity.BookOrderHeader;
import com.mycompany.ebook.entity.User;
import com.mycompany.ebook.repository.BookOrderDetailRepository;
import com.mycompany.ebook.repository.BookOrderHeaderRepository;
import com.mycompany.ebook.repository.BookRepository;
import com.mycompany.ebook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/orders")
public class BookOrderHeaderController {

    @Autowired
    private BookOrderHeaderRepository bookOrderHeaderRepository;

    @Autowired
    private BookOrderDetailRepository bookOrderDetailRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody BookOrderHeader header) {

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
            // Generate random integers for tracking number
            int int_random = ThreadLocalRandom.current().nextInt();
            header.setTrackingNumber(Integer.toString(int_random));
            header.setOrderDate(LocalDateTime.now());

            Double totalPrice = 0.00;
            for (BookOrderDetail bookOrderDetail : header.getDetails()) {
                Book book = bookRepository.getReferenceById(bookOrderDetail.getBook().getId());
                totalPrice += book.getPrice() * bookOrderDetail.getOrdered();
                bookOrderDetail.setOrderHeader(header);
            }
            header.setTotalPrice(totalPrice);

            BookOrderHeader savedOrder = bookOrderHeaderRepository.save(header);
            return ResponseEntity.ok(savedOrder);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Book order");
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOrder(@RequestBody BookOrderHeader header) {

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

            //Delete previously loaded order details
            bookOrderHeaderRepository.deleteById(header.getId());

            // Generate random integers for tracking number
            int int_random = ThreadLocalRandom.current().nextInt();
            header.setTrackingNumber(Integer.toString(int_random));
            header.setOrderDate(LocalDateTime.now());

            Double totalPrice = 0.00;
            for (BookOrderDetail bookOrderDetail : header.getDetails()) {
                Book book = bookRepository.getReferenceById(bookOrderDetail.getBook().getId());
                totalPrice += book.getPrice() * bookOrderDetail.getOrdered();
                bookOrderDetail.setOrderHeader(header);
            }
            header.setTotalPrice(totalPrice);

            BookOrderHeader savedOrder = bookOrderHeaderRepository.save(header);
            return ResponseEntity.ok(savedOrder);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Book order");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Long id) {

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
            bookOrderHeaderRepository.deleteById(id);
            return new ResponseEntity<>("Order has been deleted", HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Book order");
        }
    }

    @GetMapping("/{id}")
    public BookOrderHeader getOrderById(@PathVariable("id") Long id) {
        return bookOrderHeaderRepository.findBookOrderHeaderById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookOrderHeader>> getBookOrders(
            @RequestParam(value = "trackingNumber", required = false) String trackingNumber,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "minOrderDate", required = false) String minOrderDate,
            @RequestParam(value = "maxOrderDate", required = false) String maxOrderDate,
            @RequestParam(value = "minTotalPrice", required = false) Double minTotalPrice,
            @RequestParam(value = "maxTotalPrice", required = false) Double maxTotalPrice
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

        List<BookOrderHeader> bookOrderHeaders = null;

         if (trackingNumber != null) {
            bookOrderHeaders = bookOrderHeaderRepository.findByTrackingNumber(trackingNumber);
        } else if (customerId != null ) {
            bookOrderHeaders = bookOrderHeaderRepository.findBookOrderHeaderByCustomerId(customerId);
        } else if (minOrderDate != null && maxOrderDate != null ) {
            LocalDate minDate = LocalDate.parse(minOrderDate);
            LocalDate maxDate = LocalDate.parse(maxOrderDate);
            bookOrderHeaders = bookOrderHeaderRepository.findByOrderDateBetween(minDate.atStartOfDay(),maxDate.atStartOfDay());
        } else if (minTotalPrice != null && maxTotalPrice != null ) {
            bookOrderHeaders = bookOrderHeaderRepository.findByTotalPriceBetween(minTotalPrice, maxTotalPrice);
        }

        return ResponseEntity.ok(bookOrderHeaders);
    }

}
