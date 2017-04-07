package com.hibernate.demo;

import javax.persistence.*;

/**
 * Created by akhil on 7/4/17.
 */

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String bookname;

    @ManyToOne
    Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

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
