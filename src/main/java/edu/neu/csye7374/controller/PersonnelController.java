package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PersonnelController {
    @Autowired
    private InventoryService inventoryService;

//    @GetMapping("/user")
//    public String loginForm(Model model) {
//        model.addAttribute("user", new Employee());
//        List<String> roles = Arrays.asList("Employee", "Admin");
//        model.addAttribute("roles", roles);
//        return "employee";
//    }
}
