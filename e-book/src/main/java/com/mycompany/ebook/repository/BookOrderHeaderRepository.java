package com.mycompany.ebook.repository;

import com.mycompany.ebook.entity.Book;
import com.mycompany.ebook.entity.BookOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookOrderHeaderRepository  extends JpaRepository<BookOrderHeader, Long> {

    //Optional<BookOrderHeader> findById(Long id);
    BookOrderHeader findBookOrderHeaderById(Long id);

    List<BookOrderHeader> findBookOrderHeaderByCustomerId(Long id);

    List<BookOrderHeader> findByTrackingNumber(String trackingNumber);

    List<BookOrderHeader> findByOrderDateBetween(LocalDateTime minDate, LocalDateTime maxDate);

    List<BookOrderHeader> findByTotalPriceBetween(Double minTotalPrice, Double maxTotalPrice);


}
