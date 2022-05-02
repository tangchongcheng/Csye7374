package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.service.InventoryService;
import edu.neu.csye7374.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    PersonnelService personnelService;


    @GetMapping("/distribute/{orderId}")
    public String updateOrderStatus(@PathVariable(name = "orderId") int orderId, Model model) {
        List<PSOrder> orders = new ArrayList<>();
        orders.add(inventoryService.getOrderById(orderId));
        model.addAttribute("PSOrderList",orders);
        model.addAttribute("orderId", orderId);
        model.addAttribute("employee", new Employee());
        List<String> employeeList =  personnelService.getAvailableEmployeeName();
        model.addAttribute("AvailableEmployeeList", employeeList);
        return "distribute";
    }

    @PostMapping("/choose/{orderId}")
    public String chooseDelivery(@ModelAttribute Employee employee,@PathVariable(name = "orderId") int orderId, Model model){
        String name = employee.getName();
        Integer employeeId = personnelService.getEmployeeByName(name).getId();
        inventoryService.distributeOrderToEmployee(orderId, employeeId);
        return "success";
    }

    @GetMapping("/back")
    public String goBack(Model model){
        List<PSOrder> PSOrderList = inventoryService.getAllOrders();
        model.addAttribute("PSOrderList", PSOrderList);
        return "admin";
    }

}
