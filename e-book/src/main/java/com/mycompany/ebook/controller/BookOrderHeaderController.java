package com.mycompany.ebook.controller;

import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.BookOrderDetail;
import com.mycompany.ebook.entity.BookOrderHeader;
import com.mycompany.ebook.repository.BookOrderDetailRepository;
import com.mycompany.ebook.repository.BookOrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public BookOrderHeader createOrder(@RequestBody BookOrderHeader header) {
        // Generate random integers for tracking number
        int int_random = ThreadLocalRandom.current().nextInt();
        header.setTrackingNumber(Integer.toString(int_random));
        header.setOrderDate(LocalDateTime.now());

        Double totalPrice = 0.00;
        for (BookOrderDetail bookOrderDetail: header.getDetails()) {
               totalPrice += bookOrderDetail.getBook().getPrice() * bookOrderDetail.getOrdered();

               bookOrderDetail.setOrderHeader(header);
        }
        header.setTotalPrice(totalPrice);

        return bookOrderHeaderRepository.save(header);
    }

    @GetMapping("/{id}")
    public BookOrderHeader getOrderById(@PathVariable("id") Long id) {
        return bookOrderHeaderRepository.findBookOrderHeaderById(id);
    }
}
