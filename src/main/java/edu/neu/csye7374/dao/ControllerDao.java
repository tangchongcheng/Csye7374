package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.item.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerDao extends JpaRepository<Controller, Integer> {
}
