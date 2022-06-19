package com.example.Library.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "BorrowedBooks")
@IdClass(BorrowedBookId.class)
public class BorrowedBook {



    @Id
    private Integer userid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "borrowed_books_itemid_seq")
    private Integer itemid;
    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowdate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date duedate;
    private Boolean returned;


    public BorrowedBook() {
    }


    public BorrowedBook(Integer userid, Book book, Date borrowdate, Date duedate, Boolean returned) {
        this.userid = userid;
        this.book = book;
        this.borrowdate = borrowdate;
        this.duedate = duedate;
        this.returned = returned;
    }
}
