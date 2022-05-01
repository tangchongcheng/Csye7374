package edu.neu.csye7374.facade.order;

import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.facade.cart.CartAPI;

public class OrderBuilder {

    PSOrder PSOrder;

    public OrderBuilder init(){
        this.PSOrder = new PSOrder();
        PSOrder.setStatus(0);
        PSOrder.setEmployeeId(0);
        long createTime = System.currentTimeMillis();
        PSOrder.setCreateTime(createTime);
        return this;
    }

    public OrderBuilder setItems(CartAPI cart){
        PSOrder.setOrderedItems(cart.getCode());
        return this;
    }


    public PSOrder build(){
        return this.PSOrder;
    }

}
