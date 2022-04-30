package edu.neu.csye7374.facade.cart.game;

import edu.neu.csye7374.entity.item.Persona5;

public class Persona5Add extends GameAPI {
    Persona5 persona5;

    public Persona5Add(Persona5 persona5) {
        this.persona5 = persona5;
    }

    @Override
    public String getGuid(){
        return persona5.getGuid();
    }

    @Override
    public String getName(){
        return persona5.getName();
    }

    @Override
    public double getPrice() {
        return persona5.getPrice();
    }

    @Override
    public String getDescription() {
        return persona5.getDescription();
    }

    @Override
    public String getPublishCompany() {
        return "Atlus";
    }

    @Override
    public String getPlatform() {
        return "PS";
    }
}
