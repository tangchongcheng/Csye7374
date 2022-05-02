package edu.neu.csye7374.dao.itemDao;

import edu.neu.csye7374.entity.item.EldenRing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EldenRingDao extends JpaRepository<EldenRing, Integer> {
    @Query("select c from EldenRing c where c.status = :status")
    List<EldenRing> findAllByStatus(@Param("status") int status);
    @Query(value = "select * from elden_ring c where c.status = 0 order by id asc limit 1 ", nativeQuery = true)
    EldenRing getOneAvailableItem();
    @Query(value = "select * from elden_ring c where c.status = 0 order by id asc limit :num ", nativeQuery = true)
    List<EldenRing> getAvailableItems(@Param("num") int num);

    @Query(value = "select * from elden_ring c where c.status = 0 ", nativeQuery = true)
    List<EldenRing> getAllItems();
}
