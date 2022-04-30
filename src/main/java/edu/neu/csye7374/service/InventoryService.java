package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.*;
import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    ControllerDao controllerDao;
    @Autowired
    EldenRingDao eldenRingDao;
    @Autowired
    MonitorDao monitorDao;
    @Autowired
    Persona5Dao persona5Dao;
    @Autowired
    PlayStationDao playStationDao;

    public Order createEmptyOrder(){
        CartFacade cartFacade = new CartFacade();
        return cartFacade.getOrder();
    }

}
