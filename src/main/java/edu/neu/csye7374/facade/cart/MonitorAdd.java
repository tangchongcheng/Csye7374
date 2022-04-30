package edu.neu.csye7374.facade.cart;

import edu.neu.csye7374.entity.item.Monitor;

public class MonitorAdd implements CartAPI {
    CartAPI cart;
    Monitor monitor;

    public MonitorAdd(CartAPI cart, Monitor monitor) {
        this.cart = cart;
        this.monitor = monitor;
    }

    @Override
    public String getCode() {
        return cart.getCode() + monitor.getGuid() + ",";
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public double getCost() {
        return cart.getCost() + monitor.getPrice();
    }
}
