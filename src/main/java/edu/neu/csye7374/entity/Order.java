package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Order {
    @Id
    private int orderId;

    private String orderedItems;

    @ManyToOne
    private Employee employee;

    private int status;

    private long createTime;

    private long intendedTime;

    private long arriveTime;
}
