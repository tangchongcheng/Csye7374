package edu.neu.csye7374.controller;

import edu.neu.csye7374.entity.CustomerOrder;
import edu.neu.csye7374.entity.item.Item;
import edu.neu.csye7374.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/add/{name}")
    public String addProduct(@PathVariable(name = "name") String name, HttpServletRequest request) {
        Item item = inventoryService.getItemByName(name);
        CustomerOrder cart = (CustomerOrder) request.getSession().getAttribute("cart");
        inventoryService.addItem(cart, item);
        return "redirect:/user";
    }
//
//    @GetMapping("/cart")
//    public String showCart(Model model) {
//        return "cart";
//    }
}
