package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;

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
            return "admin";
        }
        return "403";
    }

    @GetMapping("/delivered/{orderId}")
    public String updateOrderStatus(@PathVariable(name = "orderId") int orderId) {
        personnelService.updateOrderById(orderId);
        return "redirect:/user";
    }

    @GetMapping("/signout")
    public String signOut() {
        return "redirect:/";
    }
}
