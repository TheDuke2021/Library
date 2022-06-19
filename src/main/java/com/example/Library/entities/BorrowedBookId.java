package com.example.Library.entities;

import java.io.Serializable;
import java.util.Objects;

public class BorrowedBookId implements Serializable {
    private Integer userid;
    private Integer itemid;

    public BorrowedBookId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBookId that = (BorrowedBookId) o;
        return Objects.equals(userid, that.userid) && Objects.equals(itemid, that.itemid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, itemid);
    }
}