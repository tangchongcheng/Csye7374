package edu.neu.csye7374.dao.InventoryDao;

import edu.neu.csye7374.entity.item.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorDao extends JpaRepository<Monitor, Integer> {
}
