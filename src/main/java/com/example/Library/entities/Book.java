package com.example.Library.entities;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    public enum Genre {
        ДЕТЕКТИВ,
        ПРИКЛЮЧЕНИЯ,
        ФАНТАСТИКА,
        РОМАН,
        ЮМОР,
        КОМИКСЫ,
        ДЕТСКИЕ_КНИГИ,
        НАУЧНЫЕ_КНИГИ,
        СПРАВОЧНЫЕ_КНИГИ
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "books_id_seq")
    private Integer id;
    private String isbn;
    private String name;
    @Enumerated(value = EnumType.STRING)
    //@Convert(converter = BookGenreConverter.class)
    private Genre genre;
    private String author;
    private String publisher;
    private Integer pages;
    private Integer quantity;
    private Integer issueyear;
    private String description;
    private byte[] cover;


    public Book(String isbn, String name, Genre genre, String author, String publisher, Integer pages, Integer quantity, Integer issueyear, String description, byte[] cover) {
        this.isbn = isbn;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.quantity = quantity;
        this.issueyear = issueyear;
        this.description = description;
        this.cover = cover;
    }
}
