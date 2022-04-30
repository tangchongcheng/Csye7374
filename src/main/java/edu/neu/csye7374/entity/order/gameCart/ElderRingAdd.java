package edu.neu.csye7374.entity.order.gameCart;

import edu.neu.csye7374.entity.order.CartAPI;

public class ElderRingAdd implements CartAPI {
    CartAPI cart;

    public ElderRingAdd(CartAPI cart) {
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
