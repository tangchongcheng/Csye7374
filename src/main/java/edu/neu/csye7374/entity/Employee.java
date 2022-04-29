package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @Id
    private int Id;
    private String name;
    private int age;
    private int orderId;
    private int status;
}
