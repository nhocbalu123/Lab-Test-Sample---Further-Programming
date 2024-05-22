package com.balu.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;

    @Column(name = "date_of_creation", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Constructor
    public Book() {}

    public Book(String name, Date dateOfCreation) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
