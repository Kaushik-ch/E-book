package com.mycompany.ebook.repository;

import com.mycompany.ebook.entity.BookOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOrderDetailRepository extends JpaRepository<BookOrderDetail, Long> {

    List<BookOrderDetail> findBookOrderDetailByOrderHeaderId(Long id);
}
