package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    @Query(value = "select count(0) from employee where username = :username and password = :password and role = :role ", nativeQuery = true)
    int findByAuth(String username, String password, String role);
}
