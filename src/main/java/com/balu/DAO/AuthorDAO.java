package com.balu.DAO;

import com.balu.config.HibernateUtil;
import com.balu.entity.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDAO {
    public void save(Author author) {
        validateAuthor(author);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            System.out.println("Author saved successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Author getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        }
    }

    public void update(Author author) {
        validateAuthor(author);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
            System.out.println("Author updated successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void validateAuthor(Author author) {
        if (author.getName() == null) {
            throw new IllegalArgumentException("Author name cannot be null");
        }
        if (author.getAcademicCredentials() == null) {
            throw new IllegalArgumentException("Author academic credentials cannot be null");
        }
    }

    public void delete(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Author> searchByName(String name, boolean ascending) {
        String order = ascending ? "asc" : "desc";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Author WHERE name LIKE :name ORDER BY name " + order;
            return session.createQuery(hql, Author.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        }
    }

    public List<Author> searchByAcademicCredentials(String credentials, boolean ascending) {
        String order = ascending ? "asc" : "desc";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Author WHERE academicCredentials LIKE :credentials ORDER BY academicCredentials " + order;
            return session.createQuery(hql, Author.class)
                    .setParameter("credentials", "%" + credentials + "%")
                    .list();
        }
    }
}
