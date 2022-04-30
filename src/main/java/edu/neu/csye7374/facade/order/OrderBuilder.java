package edu.neu.csye7374.facade.order;

import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.facade.cart.CartAPI;

public class OrderBuilder {

    Order order;

    public OrderBuilder init(){
        this.order = new Order();
        order.setStatus(0);
        order.setEmployeeId(0);
        long createTime = System.currentTimeMillis();
        order.setCreateTime(createTime);
        return this;
    }

    public OrderBuilder setItems(CartAPI cart){
        order.setOrderedItems(cart.getCode());
        return this;
    }


    public Order build(){
        return this.order;
    }

}
