package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    int findByAuth(String username, String password, String role);
}
