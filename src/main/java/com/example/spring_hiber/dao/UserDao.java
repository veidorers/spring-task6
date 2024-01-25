package com.example.spring_hiber.dao;

import com.example.spring_hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u", User.class).getResultList();
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    public User findById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    public void delete(Long id) {
        var session = sessionFactory.getCurrentSession();
        var user = session.get(User.class, id);
        session.remove(user);
    }
}
