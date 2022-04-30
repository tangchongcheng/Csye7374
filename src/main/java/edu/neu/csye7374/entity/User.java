package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private int Id;
    private String username;
    private String password;
    private String role;
    private int age;
    private int orderId;
    private int status;
}
