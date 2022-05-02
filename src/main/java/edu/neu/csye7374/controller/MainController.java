package edu.neu.csye7374.controller;
import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.service.CustomerService;
import edu.neu.csye7374.service.InventoryService;
import edu.neu.csye7374.service.PersonnelService;
import edu.neu.csye7374.service.UserService;
import edu.neu.csye7374.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new Employee());
        List<String> roles = Arrays.asList("Employee", "Admin", "Customer");
        model.addAttribute("roles", roles);
        return "index";
    }

    @GetMapping("/user")
    public String getUser(Model model, HttpSession httpSession) {
        Employee user = (Employee) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            if (user.getRole().equalsIgnoreCase("Employee")) {
                List<PSOrder> psOrders = personnelService.getOrderByIds(user.getOrderIds());
                model.addAttribute("psOrders", psOrders);
                return "employee";
            }
            if (user.getRole().equalsIgnoreCase("Admin")) {
                List<PSOrder> PSOrderList = inventoryService.getAllOrders();
                model.addAttribute("PSOrderList", PSOrderList);
                return "admin";
            }
            List<Product> products = inventoryService.getAllProduct();
            model.addAttribute("products", products);
            return "customer";
        }
        return "403";
    }

    @PostMapping("/user")
    public String loginForm(@ModelAttribute Employee user, Model model, HttpServletRequest request) {
        Employee target = userService.getByAuth(user);
        if (target != null) {
            model.addAttribute("user", target);
            request.getSession().setAttribute("user", target);
            if (target.getRole().equalsIgnoreCase("Employee")) {
                List<PSOrder> psOrders = personnelService.getOrderByIds(target.getOrderIds());
                model.addAttribute("psOrders", psOrders);
                return "employee";
            }
            else if (target.getRole().equalsIgnoreCase("Admin")) {
                List<PSOrder> PSOrderList = inventoryService.getAllOrders();
                model.addAttribute("PSOrderList", PSOrderList);
                return "admin";
            }else{
                List<Product> products = inventoryService.getAllProduct();
                model.addAttribute("products", products);
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomerId(target.getId());
                customerOrder.setId(customerService.getMaxOrderId()+1);
                request.getSession().setAttribute("cart", customerOrder);
                return "customer";
            }
        }
        return "403";
    }
}
