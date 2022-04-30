package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.EmployeeDao;
import edu.neu.csye7374.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private EmployeeDao employeeDao;

    public Employee getByAuth(Employee user) {
        return employeeDao.getByAuth(user.getUsername(), user.getPassword(), user.getRole());
    }
}
