package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.service.InventoryService;
import edu.neu.csye7374.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private InventoryService inventoryService;

//    @GetMapping("/customer")
//    public String itemList(Model model) {
//        List<Product> products = inventoryService.getAllProduct();
//        model.addAttribute("products", products);
//        return "customer";
//    }

    @GetMapping("/add")
    public String showEditProductPage(Model model) {
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("customerOrder", session.getAttribute("customerOrder"));
        return "cart";
    }

    @GetMapping("/place/{customerOrderId}")
    public String placeOrder(Model model, @PathVariable(name = "customerOrderId") int customerOrderId) {
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
}
