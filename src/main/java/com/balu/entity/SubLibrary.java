package com.balu.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sublibrary")
public class SubLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sublibrary_id", nullable = false)
    private List<Author> authors;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
