package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.Employee;
import edu.neu.csye7374.service.InventoryService;
import edu.neu.csye7374.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private InventoryService inventoryService;

//    @GetMapping("/customer")
//    public String itemList(Model model) {
//        List<Product> products = inventoryService.getAllProduct();
//        model.addAttribute("products", products);
//        return "customer";
//    }

    @RequestMapping("/add")
    public String showEditProductPage(Model model) {
        return "redirect:/";
    }
//
//    @GetMapping("/cart")
//    public String showCart(Model model) {
//        return "cart";
//    }
}
