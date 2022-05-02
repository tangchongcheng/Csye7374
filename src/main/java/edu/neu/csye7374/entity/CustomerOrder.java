package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomerOrder {
    @Id
    private int id;
    private String name;
    private int customerId;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int controllerNo;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int monitorNo;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int playstationNo;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int eldenringNo;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int persona5No;

}
