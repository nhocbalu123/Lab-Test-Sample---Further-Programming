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
        AuthorDAO authorDAO = new AuthorDAO();

        /*// Create Author and Book objects
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
        bookDAO.save(book);*/

        /*// Retrieve and update a Book
        Book retrievedBook = bookDAO.getById(book.getId());
        if (retrievedBook != null) {
            retrievedBook.setName("Updated Book Name");
            bookDAO.update(retrievedBook);
        } else {
            System.out.println("Book not found.");
        }*/

        // Search example
        List<SubLibrary> subLibraries = subLibraryDAO.searchBySubject("Science", true);
        for (SubLibrary sl : subLibraries) {
            System.out.println("SubLibrary ID: " + sl.getId() + ", Subject: " + sl.getSubject());
        }

//        List<Author> authorsByName = authorDAO.searchByName("John", false);
//        for (Author author : authorsByName) {
//            System.out.println("Author: " + author.getName());
//        }
//
//        List<Author> authorsByCredentials = authorDAO.searchByAcademicCredentials("PhD", true);
//        for (Author author : authorsByCredentials) {
//            System.out.println("Author: " + author.getAcademicCredentials());
//        }
//
//        List<Book> booksByName = bookDAO.searchByName("Java", true);
//        for (Book book : booksByName) {
//            System.out.println("Book: " + book.getName());
//        }
//
//        List<Book> booksByDate = bookDAO.searchByDateOfCreation("2022-01-01", false);
//        for (Book book : booksByDate) {
//            System.out.println("Book: " + book.getDateOfCreation());
//        }

        HibernateUtil.shutdown();
    }
}
