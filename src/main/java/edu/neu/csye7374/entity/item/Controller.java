package edu.neu.csye7374.entity.item;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Controller {
    @Id
    private int id;
}
