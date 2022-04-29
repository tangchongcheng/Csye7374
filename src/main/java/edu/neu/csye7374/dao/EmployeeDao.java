package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
