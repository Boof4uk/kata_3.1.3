package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Component
@Transactional
public class UserServiceImplementation implements UserService {

    private final UserDAO userDao;

    @Autowired
    UserServiceImplementation(UserDAO userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);

    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public List<User> getUserTable() {
        return userDao.getUserTable();
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

}
