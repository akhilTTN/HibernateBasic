package com.hibernate.demo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by akhil on 24/3/17.
 */
@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    @Column(name="fname")
    String firstName;
    @Column(name="lname")
    String lastName;
    @Transient
    int age;
    LocalDate dob;

    @ElementCollection
    List<String> subjects= new ArrayList<>();

    @Embedded
    Address address;

    @OneToOne
    Book book;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", address=" + address +
                '}';
    }
}
