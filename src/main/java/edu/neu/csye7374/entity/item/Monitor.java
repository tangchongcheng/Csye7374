package edu.neu.csye7374.entity.item;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monitor extends Item {
    @Id
    private int id;
}
