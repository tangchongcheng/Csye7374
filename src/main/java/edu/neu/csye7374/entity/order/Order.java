package edu.neu.csye7374.entity.order;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    private int orderId;

    @Transient
    private CartAPI cart;

    private String orderedItems;

    @ColumnDefault("0")
    private int employeeId;

    private int status;
}
