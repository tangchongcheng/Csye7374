package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from employee where username = :username and password = :password and role = :role ", nativeQuery = true)
    Employee getByAuth(String username, String password, String role);

    @Query(value = "select id from Employee where status = 0", nativeQuery = true)
    List<Integer> getAvailableEmployeeId();

    @Query(value = "select * from employee where name = :name", nativeQuery = true)
    Employee getEmployeeByName(String name);
}
