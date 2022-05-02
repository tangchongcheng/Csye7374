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

    @GetMapping("/delivered/{orderId}")
    public String updateOrderStatus(@PathVariable(name = "orderId") int orderId) {
        personnelService.updateOrderById(orderId);
        return "redirect:/user";
    }

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("cart");
        return "redirect:/";
    }
}
