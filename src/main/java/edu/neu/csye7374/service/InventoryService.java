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
        CartFacade cartFacade = new CartFacade(customerOrder.getCustomerId());
        cartFacade = addControllerBatch(customerOrder.getControllerNo(),cartFacade);
        cartFacade = addMonitorBatch(customerOrder.getMonitorNo(), cartFacade);
        cartFacade = addPlayStationBatch(customerOrder.getPlaystationNo(), cartFacade);
        cartFacade = addEldenRingBatch(customerOrder.getEldenringNo(), cartFacade);
        cartFacade = addPersona5Batch(customerOrder.getPersona5No(), cartFacade);
        PSOrder order = cartFacade.getPSOrder();
        Integer maxID = customerDao.getMaxOrderId() == null ? 0 : customerDao.getMaxOrderId() + 1;
        order.setOrderId(maxID);
        orderDao.save(order);
        return order;
    }

    public double getEstimatedPrice(CustomerOrder customerOrder){
        double controllerPrice = controllerDao.getAllItems().get(0).getPrice();
        double eldenringPrice = eldenRingDao.getAllItems().get(0).getPrice();
        double monitorPrice = monitorDao.getAllItems().get(0).getPrice();
        double persona5Price = persona5Dao.getAllItems().get(0).getPrice();
        double playstationPrice = playStationDao.getAllItems().get(0).getPrice();
        return customerOrder.getMonitorNo()*monitorPrice + customerOrder.getPersona5No()*persona5Price +
                customerOrder.getControllerNo()*controllerPrice + customerOrder.getPlaystationNo()*playstationPrice +
                customerOrder.getEldenringNo()*eldenringPrice;
    }

    public List<PSOrder> getOrdersByCustomerId(Integer customerId){
        return orderDao.findAllByCustomer(customerId);
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

    private void init() {
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
        this.init();
        return products;
    }

    public Item getItemByName(String name) {
        Item item = null;
        if (name.equalsIgnoreCase("controller")) {
            item = controllerDao.getOneAvailableItem();
        }
        if (name.equalsIgnoreCase("eldenring")) {
            item = eldenRingDao.getOneAvailableItem();
        }
        if (name.equalsIgnoreCase("monitor")) {
            item = monitorDao.getOneAvailableItem();
        }
        if (name.equalsIgnoreCase("persona5")) {
            item = persona5Dao.getOneAvailableItem();
        }
        if (name.equalsIgnoreCase("playstation")) {
            item = playStationDao.getOneAvailableItem();
        }
        return item;
    }

    public void addItem(CustomerOrder cart, Item item) {
        if (item instanceof Controller) {
            cart.setControllerNo(cart.getControllerNo() + 1);
            return;
        }
        if (item instanceof EldenRing) {
            cart.setEldenringNo(cart.getEldenringNo() + 1);
            return;
        }
        if (item instanceof Monitor) {
            cart.setMonitorNo(cart.getMonitorNo() + 1);
            return;
        }
        if (item instanceof Persona5) {
            cart.setPersona5No(cart.getPersona5No() + 1);
            return;
        }
        cart.setPlaystationNo(cart.getPlaystationNo() + 1);
    }
}
