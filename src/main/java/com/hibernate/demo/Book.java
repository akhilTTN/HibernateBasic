package com.hibernate.demo;

import javax.persistence.Entity;

/**
 * Created by akhil on 7/4/17.
 */

@Entity
public class Book {
    String bookname;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                '}';
    }
}
