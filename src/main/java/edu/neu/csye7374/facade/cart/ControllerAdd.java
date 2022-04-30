package edu.neu.csye7374.facade.cart;

import edu.neu.csye7374.entity.item.Controller;

public class ControllerAdd implements CartAPI {
    CartAPI cart;

    Controller controller;

    public ControllerAdd(CartAPI cart, Controller controller) {
        this.cart = cart;
        this.controller = controller;
    }

    @Override
    public String getCode() {
        return cart.getCode() + controller.getGuid() + ",";
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public double getCost() {
        return cart.getCost() + controller.getPrice();
    }
}
