package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerOrder, Integer> {
    @Query("select c from CustomerOrder c where c.name=:name")
    CustomerOrder findCustomerOrderByName(@Param("name") String name);
}
