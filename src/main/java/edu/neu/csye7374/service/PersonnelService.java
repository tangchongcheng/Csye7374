package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.EmployeeDao;
import edu.neu.csye7374.dao.OrderDao;
import edu.neu.csye7374.dao.itemDao.*;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.entity.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnelService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    EmployeeDao employeeDao;
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

    public List<PSOrder> getOrderByIds(String orderIds) {
        List<PSOrder> res = new ArrayList<>();
        if (orderIds.isEmpty()) {
            return res;
        }
        String[] ids = orderIds.split(",");
        for (String id : ids) {
            PSOrder psOrder = orderDao.getById(Integer.valueOf(id));
            res.add(psOrder);
        }
        return res;
    }

    public PSOrder updateOrderById(int orderId) {
        PSOrder psOrder = orderDao.getById(orderId);
        psOrder.setStatus(2);
        String orderedItems = psOrder.getOrderedItems();
        updateItemStatus(orderedItems);
        return orderDao.save(psOrder);
    }

    private void updateItemStatus(String orderedItems) {
        String[] guids = orderedItems.split(",");
        for (String guid : guids) {
            if (guid.contains("controller")) {
                Controller controller = controllerDao.getByGuid(guid);
                controller.setStatus(1);
                controllerDao.save(controller);
            }
            if (guid.contains("elden")) {
                EldenRing eldenRing = eldenRingDao.getByGuid(guid);
                eldenRing.setStatus(1);
                eldenRingDao.save(eldenRing);
            }
            if (guid.contains("monitor")) {
                Monitor monitor = monitorDao.getByGuid(guid);
                monitor.setStatus(1);
                monitorDao.save(monitor);
            }
            if (guid.contains("persona")) {
                Persona5 persona5 = persona5Dao.getByGuid(guid);
                persona5.setStatus(1);
                persona5Dao.save(persona5);
            }
            if (guid.contains("psfive")) {
                PlayStation playStation = playStationDao.getByGuid(guid);
                playStation.setStatus(1);
                playStationDao.save(playStation);
            }
        }
    }

    public List<Integer> getAvailableEmployeeId(){
        List<Employee> employees = employeeDao.findAll();
        List<Integer> result = new ArrayList<>();
        for(Employee e:employees){
            if(e.getStatus()==0)
            result.add(e.getId());
        }
        return result;
    }
}
