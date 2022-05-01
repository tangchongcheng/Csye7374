package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.OrderDao;
import edu.neu.csye7374.entity.PSOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnelService {
    @Autowired
    private OrderDao orderDao;
    public List<PSOrder> getOrderByIds(String orderIds) {
        String[] ids = orderIds.split(",");
        List<PSOrder> res = new ArrayList<>();
        for (String id : ids) {
            PSOrder PSOrder = orderDao.getById(Integer.valueOf(id));
            res.add(PSOrder);
        }
        return res;
    }
}
