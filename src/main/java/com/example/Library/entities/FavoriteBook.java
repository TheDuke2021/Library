package com.example.Library.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "FavoriteBooks")
@IdClass(FavoriteBook.FavoriteBookId.class)
public class FavoriteBook {

    @EqualsAndHashCode
    @NoArgsConstructor
    class FavoriteBookId implements Serializable {
        private Integer userid;
        private Integer itemid;
    }

    @Id
    private Integer userid;
    @Id
    private Integer itemid;
    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
