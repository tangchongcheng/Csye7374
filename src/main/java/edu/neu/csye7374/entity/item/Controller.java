package edu.neu.csye7374.entity.item;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Component
public class Controller extends Item {
    @Id
    private int id;
}
