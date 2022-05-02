package edu.neu.csye7374.dao.itemDao;

import edu.neu.csye7374.entity.item.Persona5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Persona5Dao extends JpaRepository<Persona5, Integer> {
    @Query("select c from Persona5 c where c.status = :status")
    List<Persona5> findAllByStatus(@Param("status") int status);
    @Query(value = "select * from Persona5 c where c.status = 0 order by id asc limit 1 ", nativeQuery = true)
    Persona5 getOneAvailableItem();
    @Query(value = "select * from Persona5 c where c.status = 0 order by id asc limit :num ", nativeQuery = true)
    List<Persona5> getAvailableItems(@Param("num") int num);

    @Query(value = "select * from Persona5 c where c.status = 0", nativeQuery = true)
    List<Persona5> getAllItems();
}
