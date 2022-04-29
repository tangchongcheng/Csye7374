package edu.neu.csye7374.entity.item;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public abstract class Item {
    private String name;
    private String description;
    private double price;
    @Id
    private int id;
}
