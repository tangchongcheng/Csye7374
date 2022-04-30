package edu.neu.csye7374.facade.cart.game;

import edu.neu.csye7374.entity.item.EldenRing;

public class EldenRingAdd extends GameAPI {
    EldenRing eldenRing;

    public EldenRingAdd(EldenRing elderRing) {
        this.eldenRing = elderRing;
    }

    @Override
    public String getGuid(){
        return eldenRing.getGuid();
    }

    @Override
    public String getName(){
        return eldenRing.getName();
    }

    @Override
    public double getPrice() {
        return eldenRing.getPrice();
    }

    @Override
    public String getDescription() {
        return eldenRing.getDescription();
    }

    @Override
    public String getPublishCompany() {
        return "FromSoftware";
    }

    @Override
    public String getPlatform() {
        return "PS";
    }
}
