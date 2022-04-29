package edu.neu.csye7374.entity.item;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Item {
    private String name;
    private String description;
    private double price;
}
