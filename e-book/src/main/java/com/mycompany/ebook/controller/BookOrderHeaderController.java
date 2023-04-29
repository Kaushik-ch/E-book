package com.mycompany.ebook.controller;

import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.BookOrderDetail;
import com.mycompany.ebook.entity.BookOrderHeader;
import com.mycompany.ebook.repository.BookOrderDetailRepository;
import com.mycompany.ebook.repository.BookOrderHeaderRepository;
import com.mycompany.ebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public BookOrderHeader createOrder(@RequestBody BookOrderHeader header) {
        // Generate random integers for tracking number
        int int_random = ThreadLocalRandom.current().nextInt();
        header.setTrackingNumber(Integer.toString(int_random));
        header.setOrderDate(LocalDateTime.now());

        Double totalPrice = 0.00;
        for (BookOrderDetail bookOrderDetail: header.getDetails()) {

               Book book =  bookRepository.getReferenceById(bookOrderDetail.getBook().getId());

               totalPrice += book.getPrice() * bookOrderDetail.getOrdered();

               bookOrderDetail.setOrderHeader(header);
        }
        header.setTotalPrice(totalPrice);

        return bookOrderHeaderRepository.save(header);
    }

    @GetMapping("/{id}")
    public List<BookOrderHeader> getOrderById(@PathVariable("id") Long id) {
        return bookOrderHeaderRepository.findBookOrderHeaderByCustomerId(104L);//.findBookOrderHeaderById(id);
    }


    @GetMapping("/search")
    public ResponseEntity<List<BookOrderHeader>> getBookOrders(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "trackingNumber", required = false) String trackingNumber,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "minOrderDate", required = false) String minOrderDate,
            @RequestParam(value = "maxOrderDate", required = false) String maxOrderDate,
            @RequestParam(value = "minTotalPrice", required = false) Double minTotalPrice,
            @RequestParam(value = "maxTotalPrice", required = false) Double maxTotalPrice
    ) {
        List<BookOrderHeader> bookOrderHeaders = null;

        if (id != null) {
            bookOrderHeaders = bookOrderHeaderRepository.findBookOrderHeaderById(id);
        } else if (trackingNumber != null) {
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
