package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {

    int findByAuth(String username, String password, String role);
}
