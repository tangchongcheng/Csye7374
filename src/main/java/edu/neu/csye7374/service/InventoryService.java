package edu.neu.csye7374.service;

import edu.neu.csye7374.dao.CustomerDao;
import edu.neu.csye7374.dao.EmployeeDao;
import edu.neu.csye7374.dao.OrderDao;
import edu.neu.csye7374.dao.itemDao.*;
import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.entity.item.*;
import edu.neu.csye7374.facade.CartFacade;
import edu.neu.csye7374.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
    @Autowired
    private List<Item> itemEntities;

    private List<Product> products;

    public PSOrder createOrderFromCustomerOrderId(Integer customerOrderId){
        CustomerOrder customerOrder = customerDao.findById(customerOrderId).orElse(null);
        if(Objects.isNull(customerOrder)) return null;
        CartFacade cartFacade = new CartFacade();
        cartFacade = addControllerBatch(customerOrder.getControllerNo(),cartFacade);
        cartFacade = addMonitorBatch(customerOrder.getMonitorNo(), cartFacade);
        cartFacade = addPlayStationBatch(customerOrder.getPlaystationNo(), cartFacade);
        cartFacade = addEldenRingBatch(customerOrder.getEldenringNo(), cartFacade);
        cartFacade = addPersona5Batch(customerOrder.getPersona5No(), cartFacade);
        return cartFacade.getPSOrder();
    }

//    public void createOrderFromAllCustomerOrder(){
//        List<CustomerOrder> orders = customerDao.findAll();
//        for(CustomerOrder o: orders){
//            orderDao.save(createOrderFromCustomerOrderId(o.getId()));
//        }
//    }

    public void distributeOrderToEmployee(Integer orderId, Integer employeeId){
        Employee employee = employeeDao.getById(employeeId);
        PSOrder PSOrder = orderDao.getById(orderId);
//        long intendedTime = System.currentTimeMillis();
//        PSOrder.setIntendedTime(intendedTime);
        PSOrder.setEmployeeId(employeeId);
        String orderIds = employee.getOrderIds();
        if (StringUtils.isEmpty(orderIds)) {
            orderIds = "" + orderId;
        } else {
            orderIds = orderIds + "," + orderId;
        }
        employee.setOrderIds(orderIds);
        PSOrder.setStatus(1);

        orderDao.save(PSOrder);
        employeeDao.save(employee);

    }

    public List<PSOrder> getAllOrders(){
        return orderDao.findAll();
    }
    public PSOrder getOrderById(Integer id){
        return orderDao.getById(id);
    }

    public List<PSOrder> getOrdersToDeliver(){
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

    @PostConstruct
    void init() {
        products = new ArrayList<>();
        System.out.println("item entity size is: " + itemEntities.size());
        for (Item itemEntity : itemEntities) {
            System.out.println("itemEntity is: " + itemEntity);
            String productName = itemEntity.getClass().getSimpleName();
            System.out.println("name is: " + productName);
            Product product = new Product();
            product.setName(productName);
            String desc = null;
            int stock = 0;
            double price = 0;
            if (productName.equalsIgnoreCase("controller")) {
                List<Controller> availableItems = controllerDao.getAllItems();
                stock = availableItems.size();
                desc = availableItems.get(0).getDescription();
                price = availableItems.get(0).getPrice();
            } else if (productName.equalsIgnoreCase("eldenring")) {
                List<EldenRing> availableItems = eldenRingDao.getAllItems();
                stock = availableItems.size();
                desc = availableItems.get(0).getDescription();
                price = availableItems.get(0).getPrice();
            } else if (productName.equalsIgnoreCase("monitor")) {
                List<Monitor> availableItems = monitorDao.getAllItems();
                stock = availableItems.size();
                desc = availableItems.get(0).getDescription();
                price = availableItems.get(0).getPrice();
            } else if (productName.equalsIgnoreCase("persona5")) {
                List<Persona5> availableItems = persona5Dao.getAllItems();
                stock = availableItems.size();
                desc = availableItems.get(0).getDescription();
                price = availableItems.get(0).getPrice();
            } else if (productName.equalsIgnoreCase("playstation")) {
                List<PlayStation> availableItems = playStationDao.getAllItems();
                stock = availableItems.size();
                desc = availableItems.get(0).getDescription();
                price = availableItems.get(0).getPrice();
            }
            product.setStock(stock);
            product.setDescription(desc);
            product.setPrice(price);
            products.add(product);
        }
    }

    public List<Product> getAllProduct() {
        return products;
    }
}
