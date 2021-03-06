package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.entity.item.Item;
import edu.neu.csye7374.service.CustomerService;
import edu.neu.csye7374.service.InventoryService;
import edu.neu.csye7374.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    CustomerService customerService;

    @RequestMapping("/add/{name}")
    public String addProduct(@PathVariable(name = "name") String name, HttpServletRequest request) {
        Item item = inventoryService.getItemByName(name);
        CustomerOrder cart = (CustomerOrder) request.getSession().getAttribute("cart");
        inventoryService.addItem(cart, item);
        return "redirect:/user";
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        List<CustomerOrder> orders = new ArrayList<>();
        CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("cart");
        System.out.println("customer order id is: " + customerOrder.getId());
        orders.add(customerOrder);
        model.addAttribute("customerOrder", customerOrder);
        model.addAttribute("customerOrderList", orders);
        model.addAttribute("price", inventoryService.getNormalEstimatedPrice(customerOrder));
        return "cart";
    }

    @GetMapping("/place/{customerOrderId}")
    public String placeOrder(Model model, @PathVariable(name = "customerOrderId") int customerOrderId, HttpSession session) {
        CustomerOrder customerOrder = (CustomerOrder) session.getAttribute("cart");
        customerService.saveOrder(customerOrder);
        PSOrder order = inventoryService.createOrderFromCustomerOrderId(customerOrderId);
        model.addAttribute("orderId", order.getOrderId());
        return "placeOrderSuccess";
    }

    @GetMapping("/view/{customerId}")
    public String viewOrderDetails(Model model, @PathVariable(name = "customerId") int customerId){
        List<PSOrder> orders = inventoryService.getOrdersByCustomerId(customerId);
        model.addAttribute("PSOrderList", orders);
        return "viewOrderDetails";
    }

    @GetMapping("/customerBack")
    public String back(Model model, HttpSession session){
        List<Product> products = inventoryService.getAllProduct();
        model.addAttribute("products", products);
        model.addAttribute("user",session.getAttribute("user"));
        return "customer";
    }
    @GetMapping("/orderPlacedBack")
    public String back2(Model model, HttpSession session){
        List<Product> products = inventoryService.getAllProduct();
        model.addAttribute("products", products);
        session.removeAttribute("cart");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomerId(((Employee)session.getAttribute("user")).getId());
        customerOrder.setId(customerService.getMaxOrderId()+1);
        session.setAttribute("cart", customerOrder);
        model.addAttribute("user",session.getAttribute("user"));
        return "customer";
    }
}
