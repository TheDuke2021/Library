package com.example.Library.data;

import com.example.Library.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    public Book findById(Integer id);
}
