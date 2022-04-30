package edu.neu.csye7374.facade.cart;

import edu.neu.csye7374.entity.item.PlayStation;

public class PlayStationAdd implements CartAPI {
    CartAPI cart;

    PlayStation playStation;

    public PlayStationAdd(CartAPI cart, PlayStation playStation) {
        this.cart = cart;
        this.playStation = playStation;
    }

    @Override
    public String getCode() { return cart.getCode() + playStation.getGuid() + ","; }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public double getCost() {return cart.getCost() + playStation.getPrice(); }
}
