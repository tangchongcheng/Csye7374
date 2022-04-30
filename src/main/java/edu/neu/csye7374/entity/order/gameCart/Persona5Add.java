package edu.neu.csye7374.entity.order.gameCart;

import edu.neu.csye7374.entity.order.CartAPI;

public class Persona5Add implements CartAPI {
    CartAPI cart;

    public Persona5Add(CartAPI cart) {
        this.cart = cart;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
