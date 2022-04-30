package edu.neu.csye7374.facade.cart.game;

import lombok.Data;

@Data
public abstract class GameAPI {
    private String guid;
    private double price;
    private String name;
    private String description;
    private String publishCompany;
    private String platform;
}
