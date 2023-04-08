package com.mycompany.ebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleAndAuthor (String Title, String Author);

    List<Book> findByIsbn(String isbn);

    List<Book> findByPrice(Double price);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Book> findByAuthorContains(String authorContains);

    List<Book> findByTitleContains(String titleContains);

    List<Book> findByDescriptionContains(String descriptionContains);

}
