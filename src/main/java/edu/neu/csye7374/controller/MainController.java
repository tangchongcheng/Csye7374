package edu.neu.csye7374.controller;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
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

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new Employee());
        List<String> roles = Arrays.asList("Employee", "Admin", "Customer");
        model.addAttribute("roles", roles);
        return "index";
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
            if (target.getRole().equalsIgnoreCase("Admin")) {
                return "admin";
            }
            List<Product> products = inventoryService.getAllProduct();
            model.addAttribute("products", products);
            return "customer";
        }
        return "403";
    }
}
