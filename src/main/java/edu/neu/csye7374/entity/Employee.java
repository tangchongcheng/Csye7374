package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    private int Id;
    private String username;
    private String password;
    private String role;
    private String name;
    private int age;
    @OneToMany
    private List<Order> orderList;
    @OneToOne
    private Order currentOrder;
    private int status;
}
