package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.UserDao;
import edu.neu.csye7374.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public boolean isAuthenticated(User user) {
        userDao.findByAuth(user.getUsername(), user.getPassword(), user.getRole());
        return
    }
}
