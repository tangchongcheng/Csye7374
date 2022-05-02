package edu.neu.csye7374.dao.itemDao;

import edu.neu.csye7374.entity.item.PlayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayStationDao extends JpaRepository<PlayStation, Integer> {
    @Query("select c from PlayStation c where c.status = :status")
    List<PlayStation> findAllByStatus(@Param("status") int status);
    @Query(value = "select * from play_station c where c.status = 0 order by id asc limit 1 ", nativeQuery = true)
    PlayStation getOneAvailableItem();
    @Query(value = "select * from play_station c where c.status = 0", nativeQuery = true)
    List<PlayStation> getAvailableItems(@Param("num") int num);

    @Query(value = "select * from play_station c where c.status = 0", nativeQuery = true)
    List<PlayStation> getAllItems();
}
