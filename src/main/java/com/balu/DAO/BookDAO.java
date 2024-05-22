package com.balu.DAO;

import com.balu.config.HibernateUtil;
import com.balu.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDAO {
    public void save(Book book) {
        validateBook(book);  // Validate the book before saving

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Book getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public void update(Book book) {
        validateBook(book);  // Validate the book before updating

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void validateBook(Book book) {
        if (book.getName() == null) {
            throw new IllegalArgumentException("Book name cannot be null");
        }
        if (book.getDateOfCreation() == null) {
            throw new IllegalArgumentException("Book date of creation cannot be null");
        }
        if (book.getAuthor() == null) {
            throw new IllegalArgumentException("Book author cannot be null");
        }
    }

    public void delete(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
