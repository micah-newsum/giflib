package com.newsum.giflib.dao;

import com.newsum.giflib.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Get all categories with Hibernate criteria
        List<Category> categories = session.createCriteria(Category.class).list();

        // Close the session
        session.close();

        return categories;
    }

    @Override
    public Category findById(Long id)
    {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class,id);
        session.close();
        return category;
    }

    @Override
    public void save(Category category) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save category
        session.save(category);

        // Commit transaction
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Category category) {

    }
}
