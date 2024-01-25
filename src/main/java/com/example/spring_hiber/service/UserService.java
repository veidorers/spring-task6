package com.example.spring_hiber.service;

import com.example.spring_hiber.dao.UserDao;
import com.example.spring_hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    public User findById(long id) {
        return userDao.findById(id);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
