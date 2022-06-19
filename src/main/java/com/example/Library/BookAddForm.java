package com.example.Library;

import com.example.Library.entities.Book;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookAddForm {

    public String isbn;
    public String name;
    public Book.Genre genre;
    public String author;
    public String publisher;
    public Integer pages;
    public Integer quantity;
    public Integer issueyear;
    public String description;
    public MultipartFile file;


}
