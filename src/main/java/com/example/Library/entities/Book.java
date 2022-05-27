package com.example.Library.entities;

import lombok.Data;

import javax.persistence.*;

@Data
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
    private Integer id;
    private String isbn;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private String author;
    private String publisher;
    private Integer pages;
    private Integer quantity;
    private Integer issueyear;
    private String description;
    private byte[] cover;


}
