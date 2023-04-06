package com.mycompany.ebook;

import java.util.List;

public class Book {

    private Long id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private Double price;
    private List<String> coverImagesUrl;

    public Book() {
    }

    public Book(Long id, String title, String author, String description, String isbn, Double price, List<String> coverImagesUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.coverImagesUrl = coverImagesUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getCoverImagesUrl() {
        return coverImagesUrl;
    }

    public void setCoverImagesUrl(List<String> coverImagesUrl) {
        this.coverImagesUrl = coverImagesUrl;
    }
}
