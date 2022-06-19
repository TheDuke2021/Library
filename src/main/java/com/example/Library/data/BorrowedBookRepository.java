package com.example.Library.data;

import com.example.Library.entities.Book;
import com.example.Library.entities.BorrowedBook;
import com.example.Library.entities.BorrowedBookId;
import org.springframework.data.repository.CrudRepository;

public interface BorrowedBookRepository extends CrudRepository<BorrowedBook, BorrowedBookId> {
}
