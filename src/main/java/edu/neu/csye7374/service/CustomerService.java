package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.CustomerDao;
import edu.neu.csye7374.entity.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public CustomerOrder saveOrder(Integer id, String name, Integer controllerNo, Integer monitorNo, Integer playstationNo, Integer eldenringNo, Integer persona5No){
        CustomerOrder order = new CustomerOrder();
        order.setId(id);
        order.setName(name);
        order.setControllerNo(controllerNo);
        order.setEldenringNo(eldenringNo);
        order.setMonitorNo(monitorNo);
        order.setPersona5No(persona5No);
        order.setPlaystationNo(playstationNo);
        return customerDao.save(order);
    }

    public CustomerOrder saveOrder(CustomerOrder order){
        return customerDao.save(order);
    }

//    public List<Integer> getAllCustomerOrderId(){
//        List<Integer> list = new ArrayList<>();
//        List<CustomerOrder> orders = customerDao.findAll();
//        for(CustomerOrder o: orders){
//            list.add(o.getId());
//        }
//        return list;
//    }

}
