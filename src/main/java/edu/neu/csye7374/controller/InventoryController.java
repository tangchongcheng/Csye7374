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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    PersonnelService personnelService;


    @PostMapping("/distribute")
    public String distributeOrders(Model model) {

        model.addAttribute("Employee", new Employee());
        List<Integer> employeeList =  personnelService.getAvailableEmployeeId();
        model.addAttribute("AvailableEmployeeList", employeeList);
        return "distribute";
    }

    @PostMapping("/choose")
    public String chooseDelivery(@ModelAttribute Employee Employee, Model model){
        Integer orderId = Employee.getOrderToDo();
        Integer employeeId = Employee.getId();
        inventoryService.distributeOrderToEmployee(orderId, employeeId);
        return "success";
    }

}
