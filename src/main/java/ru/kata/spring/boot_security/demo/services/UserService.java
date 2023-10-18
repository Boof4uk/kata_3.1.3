package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findUserById(int id);
    public void deleteUser(int id);
    public void saveUser(User user);
    public User findUserByUsername(String username);
    public void updateUser(User user, int id);
}
