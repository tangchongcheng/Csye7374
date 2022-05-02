package edu.neu.csye7374.entity.item;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Item {
    @Column(nullable = false, columnDefinition = "int default 0")
    private int orderId;
    private String name;
    private String description;
    private double price;
    private int status;
    private String guid;
}
