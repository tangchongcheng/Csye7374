package edu.neu.csye7374.facade.cart;

import edu.neu.csye7374.facade.cart.game.GameAPI;

public class GameAdaptor implements CartAPI {

    GameAPI game;
    CartAPI cart;

    public GameAdaptor(CartAPI cart, GameAPI game) {
        this.cart = cart;
        this.game = game;
    }

    @Override
    public String getCode() {
        return cart.getCode() + game.getGuid() + ",";
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public double getCost() {
        return cart.getCost() + game.getPrice();
    }
}
