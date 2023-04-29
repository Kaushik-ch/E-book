package com.mycompany.ebook.entity;

import javax.persistence.*;

@Entity
@Table(name= "BOOK_ORDER_DETAIL")
public class BookOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private BookOrderHeader orderHeader;

    //@Column(name = "BOOK_ID")
    @OneToOne
    @JoinColumn(name= "BOOK_ID")
    private Book book;

    @Column(name = "ORDERED")
    private Long ordered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public BookOrderHeader getOrderHeader() {
        return orderHeader;
    }*/

    public void setOrderHeader(BookOrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Book getBook() {
        return book;
    }

   /* public void setBook(Book book) {
        this.book = book;
    }*/

    public Long getOrdered() {
        return ordered;
    }

    public void setOrdered(Long ordered) {
        this.ordered = ordered;
    }
}
