package edu.neu.csye7374.dao;

import edu.neu.csye7374.entity.item.PlayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayStationDao extends JpaRepository<PlayStation, Integer> {

}
