package com.balu.DAO;

import com.balu.config.HibernateUtil;
import com.balu.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SubLibraryDAO {
        public void save(SubLibrary subLibrary) {
            validateSubLibrary(subLibrary);
    
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.save(subLibrary);
                transaction.commit();
                System.out.println("SubLibrary saved successfully");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

    public SubLibrary getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(SubLibrary.class, id);
        }
    }

    public void update(SubLibrary subLibrary) {
        validateSubLibrary(subLibrary);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(subLibrary);
            transaction.commit();
            System.out.println("SubLibrary updated successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void validateSubLibrary(SubLibrary subLibrary) {
        if (subLibrary.getSubject() == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        if (subLibrary.getAuthors() == null || subLibrary.getAuthors().isEmpty()) {
            throw new IllegalArgumentException("Authors list cannot be null or empty");
        }

        for (Author author : subLibrary.getAuthors()) {
            if (author.getName() == null || author.getAcademicCredentials() == null) {
                throw new IllegalArgumentException("Author name and academic credentials cannot be null");
            }
        }
    }

    public List<SubLibrary> searchBySubject(String subject, boolean ascending) {
        String order = ascending ? "asc" : "desc";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM SubLibrary WHERE subject LIKE :subject ORDER BY subject " + order;
            return session.createQuery(hql, SubLibrary.class)
                    .setParameter("subject", "%" + subject + "%")
                    .list();
        }
    }
}
