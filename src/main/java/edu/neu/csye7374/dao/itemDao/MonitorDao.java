package edu.neu.csye7374.dao.itemDao;

import edu.neu.csye7374.entity.item.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorDao extends JpaRepository<Monitor, Integer> {
    @Query("select c from Monitor c where c.status = :status")
    List<Monitor> findAllByStatus(@Param("status") int status);
    @Query(value = "select * from monitor c where c.status = 0 order by id asc limit 1 ", nativeQuery = true)
    Monitor getOneAvailableItem();
    @Query(value = "select * from monitor c where c.status = 0 order by id asc limit :num ", nativeQuery = true)
    List<Monitor> getAvailableItems(@Param("num") int num);

    @Query(value = "select * from monitor c where c.status = 0", nativeQuery = true)
    List<Monitor> getAllItems();

    @Query(value = "select * from Monitor c where c.guid = :guid", nativeQuery = true)
    Monitor getByGuid(String guid);
}
