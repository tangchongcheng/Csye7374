package edu.neu.csye7374.dao.itemDao;

import edu.neu.csye7374.entity.item.Controller;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerDao extends JpaRepository<Controller, Integer> {
    @Query("select c from Controller c where c.status = :status")
    List<Controller> findAllByStatus(@Param("status") int status);

    @Query(value = "select * from controller c where c.status = 0 order by id asc limit 1 ", nativeQuery = true)
    Controller getOneAvailableItem();

    @Query(value = "select * from controller c where c.status = 0 order by id asc limit :num ", nativeQuery = true)
    List<Controller> getAvailableItems(@Param("num") int num);

    @Query(value = "select * from controller c where c.status = 0", nativeQuery = true)
    List<Controller> getAllItems();

    @Query(value = "select * from Controller c where c.guid = :guid", nativeQuery = true)
    Controller getByGuid(String guid);
}
