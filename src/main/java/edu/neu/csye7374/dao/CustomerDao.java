package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<CustomerOrder, Integer> {
    @Query("select c from CustomerOrder c where c.name=:name")
    Optional<CustomerOrder> findCustomerOrderByName(@Param("name") String name);
    @Query(value = "select max(id) from customer_order", nativeQuery = true)
    Integer getMaxOrderId();
}
