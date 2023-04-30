package com.mycompany.ebook.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK_ORDER_HEADER")
public class BookOrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL)
    private List<BookOrderDetail> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<BookOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BookOrderDetail> details) {
        this.details = details;
    }
}
