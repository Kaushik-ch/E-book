package com.mycompany.ebook.repository;

import com.mycompany.ebook.entity.BookOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookOrderHeaderRepository  extends JpaRepository<BookOrderHeader, Long> {

    //Optional<BookOrderHeader> findById(Long id);
    BookOrderHeader findBookOrderHeaderById(Long id);

    BookOrderHeader findBookOrderHeaderByCustomerId(Long id);
}
