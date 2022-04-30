package edu.neu.csye7374.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomerOrder {
    @Id
    private int id;

    private String name;

    private int controllerNo;
    private int monitorNo;
    private int playstationNo;
    private int eldenringNo;
    private int persona5No;

}
