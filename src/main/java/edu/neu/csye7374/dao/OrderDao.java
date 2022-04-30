package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.entity.item.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    @Query("select c from Order c where c.status = :status")
    List<Order> findAllByStatus(@Param("status") int status);
}
