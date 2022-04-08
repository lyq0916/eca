package com.lyq.eca.service;

import com.lyq.eca.dao.UserDao;
import com.lyq.eca.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public boolean updatephotoByUsername(String username, String photo) {
        User user=userDao.findByUsername(username);
        user.setPhoto(photo);
        try {
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}