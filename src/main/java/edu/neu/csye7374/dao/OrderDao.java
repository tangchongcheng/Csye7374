package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.PSOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<PSOrder, Integer> {
    @Query("select c from PSOrder c where c.status = :status")
    List<PSOrder> findAllByStatus(@Param("status") int status);
    @Query("select c from PSOrder c where c.customerId = :customerId")
    List<PSOrder> findAllByCustomer(@Param("customerId") int customerId);
    @Query(value = "select max(order_id) from psorder", nativeQuery = true)
    Integer getMaxOrderId();
}
