package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    InventoryService inventoryService;



    @GetMapping("/distribute")
    public String distributePage(Model model) {
        List<PSOrder> ordersToDeliver = inventoryService.getOrdersToDeliver();
        model.addAttribute("PSOrdersToDeliver", ordersToDeliver);
        return "distribute";
    }

    @PostMapping("/distribute")
    public String distributeOrders(Model model) {
        Integer orderId = (Integer) model.getAttribute("PSOrderId");
        Integer employeeId = (Integer) model.getAttribute("employeeId");
        inventoryService.distributeOrderToEmployee(orderId, employeeId);
        return "admin";
    }

}
