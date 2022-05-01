package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.EmployeeDao;
import edu.neu.csye7374.dao.OrderDao;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
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

    public List<PSOrder> getOrderByIds(String orderIds) {
        String[] ids = orderIds.split(",");
        List<PSOrder> res = new ArrayList<>();
        for (String id : ids) {
            PSOrder psOrder = orderDao.getById(Integer.valueOf(id));
            res.add(psOrder);
        }
        return res;
    }

    public PSOrder updateOrderById(int orderId) {
        PSOrder psOrder = orderDao.getById(orderId);
        psOrder.setStatus(2);
        return orderDao.save(psOrder);
    }

    public List<Integer> getAvailableEmployeeId(){
        List<Employee> employees = employeeDao.findAll();
        List<Integer> result = new ArrayList<>();
        for(Employee e:employees){
            result.add(e.getId());
        }
        return result;
    }
}
