package edu.neu.csye7374.entity;

import edu.neu.csye7374.entity.item.Item;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    private int orderId;

    private String orderedItems;

    private int employeeId;

    private int status;

    private long createTime;

    private long intendedTime;

    private long arriveTime;
}
