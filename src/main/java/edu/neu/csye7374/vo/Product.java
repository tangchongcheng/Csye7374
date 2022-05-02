package edu.neu.csye7374.vo;

import lombok.Data;

@Data
public class Product {
    private String name;
    private String description;
    private double price;
    private int stock;
}
