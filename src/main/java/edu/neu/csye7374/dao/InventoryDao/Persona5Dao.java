package edu.neu.csye7374.dao.InventoryDao;

import edu.neu.csye7374.entity.item.Persona5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Persona5Dao extends JpaRepository<Persona5, Integer> {
}
