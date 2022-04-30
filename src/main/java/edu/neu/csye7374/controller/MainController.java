package edu.neu.csye7374.controller;
import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.service.PersonnelService;
import edu.neu.csye7374.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("user", new Employee());
        List<String> roles = Arrays.asList("Employee", "Admin");
        model.addAttribute("roles", roles);
        return "index";
    }

    @PostMapping("/user")
    public String loginForm(@ModelAttribute Employee user, Model model) {
        Employee target = userService.getByAuth(user);
        if (target != null) {
            model.addAttribute("user", target);
            if (target.getRole().equalsIgnoreCase("Employee")) {
                List<Order> orders = personnelService.getOrderByIds(target.getOrderIds());
                model.addAttribute("orders", orders);
                return "employee";
            }
            return "admin";
        }
        return "403";
    }
}
