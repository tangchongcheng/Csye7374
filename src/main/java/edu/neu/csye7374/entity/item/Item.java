package edu.neu.csye7374.entity.item;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Item {
    private int orderId;
    private String name;
    private String description;
    private double price;
    private int status;
    private String guid;
}
