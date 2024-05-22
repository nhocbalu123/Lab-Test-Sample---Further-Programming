package com.balu;


import com.balu.DAO.*;
import com.balu.config.HibernateUtil;
import com.balu.entity.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create DAO instances
        BookDAO bookDAO = new BookDAO();
        SubLibraryDAO subLibraryDAO = new SubLibraryDAO();

        // Create Author and Book objects
        Author author = new Author();
        author.setName("nhocbalu");
        author.setAcademicCredentials("PhD");

        Book book = new Book();
        book.setName("Sample Book");
        book.setDateOfCreation(new Date());
        book.setAuthor(author);

        // Create SubLibrary object
        SubLibrary subLibrary = new SubLibrary();
        subLibrary.setSubject("Science");
        subLibrary.setAuthors(Arrays.asList(author));

        // Save the SubLibrary and Book
        subLibraryDAO.save(subLibrary);
        bookDAO.save(book);

        /*// Retrieve and update a Book
        Book retrievedBook = bookDAO.getById(book.getId());
        if (retrievedBook != null) {
            retrievedBook.setName("Updated Book Name");
            bookDAO.update(retrievedBook);
        } else {
            System.out.println("Book not found.");
        }*/


        HibernateUtil.shutdown();
    }
}
