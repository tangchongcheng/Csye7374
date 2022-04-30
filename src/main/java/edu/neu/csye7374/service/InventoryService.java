package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.CustomerDao;
import edu.neu.csye7374.dao.EmployeeDao;
import edu.neu.csye7374.dao.OrderDao;
import edu.neu.csye7374.dao.itemDao.*;
import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.entity.item.*;
import edu.neu.csye7374.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    @Autowired
    CustomerDao customerDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    EmployeeDao employeeDao;


    public Order createOrderFromCustomerOrder(String customerName){
        CustomerOrder customerOrder = customerDao.findCustomerOrderByName(customerName);
        if(Objects.isNull(customerOrder)) return null;
        CartFacade cartFacade = new CartFacade();
        cartFacade = addControllerBatch(customerOrder.getControllerNo(),cartFacade);
        cartFacade = addMonitorBatch(customerOrder.getMonitorNo(), cartFacade);
        cartFacade = addPlayStationBatch(customerOrder.getPlaystationNo(), cartFacade);
        cartFacade = addEldenRingBatch(customerOrder.getEldenringNo(), cartFacade);
        cartFacade = addPersona5Batch(customerOrder.getPersona5No(), cartFacade);
        return cartFacade.getOrder();
    }

    public void distributeOrderToEmployee(Integer orderId, Integer employeeId){
        Employee employee = employeeDao.findById(employeeId).orElse(null);
        Order order = orderDao.findById(orderId).orElse(null);
        if(Objects.isNull(employee) || Objects.isNull(order)) return;
//        long intendedTime = System.currentTimeMillis();
//        order.setIntendedTime(intendedTime);
        order.setEmployeeId(employeeId);
        employee.setOrderId(orderId);
        order.setStatus(1);

        orderDao.save(order);
        employeeDao.save(employee);

    }

    public List<Order> getAllOrders(){
        return orderDao.findAll();
    }

    public List<Order> getOrdersToDeliver(){
        return orderDao.findAllByStatus(0);
    }

    public CartFacade addControllerBatch(int num, CartFacade cartFacade){
        List<Controller> items = controllerDao.getAvailableItems(num);
        for(Controller c:items){
            cartFacade.addController(c);
        }
        return cartFacade;
    }
    public CartFacade addMonitorBatch(int num, CartFacade cartFacade){
        List<Monitor> items = monitorDao.getAvailableItems(num);
        for(Monitor c:items){
            cartFacade.addMonitor(c);
        }
        return cartFacade;
    }
    public CartFacade addPlayStationBatch(int num, CartFacade cartFacade){
        List<PlayStation> items = playStationDao.getAvailableItems(num);
        for(PlayStation c:items){
            cartFacade.addPlayStation(c);
        }
        return cartFacade;
    }
    public CartFacade addEldenRingBatch(int num, CartFacade cartFacade){
        List<EldenRing> items = eldenRingDao.getAvailableItems(num);
        for(EldenRing c:items){
            cartFacade.addEldenRing(c);
        }
        return cartFacade;
    }
    public CartFacade addPersona5Batch(int num, CartFacade cartFacade){
        List<Persona5> items = persona5Dao.getAvailableItems(num);
        for(Persona5 c:items){
            cartFacade.addPersona5(c);
        }
        return cartFacade;
    }
}
