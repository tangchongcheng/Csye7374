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

    @GetMapping("/admin")
    public String inventoryHomePage(Model model) {
        List<PSOrder> PSOrderList = inventoryService.getAllOrders();
        model.addAttribute("orderList", PSOrderList);
        return "admin";
    }

    @GetMapping("/distribute")
    public String distributePage(Model model) {
        List<PSOrder> ordersToDeliver = inventoryService.getOrdersToDeliver();
        model.addAttribute("ordersToDeliver", ordersToDeliver);
        return "distribute";
    }

    @PostMapping("/distribute")
    public String distributeOrders(Model model) {
        Integer orderId = (Integer) model.getAttribute("orderId");
        Integer employeeId = (Integer) model.getAttribute("employeeId");
        inventoryService.distributeOrderToEmployee(orderId, employeeId);
        return "admin";
    }

}
