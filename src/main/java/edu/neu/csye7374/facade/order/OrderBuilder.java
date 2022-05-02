package edu.neu.csye7374.facade.order;

import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.facade.cart.CartAPI;

public class OrderBuilder {

    PSOrder PSOrder;

    public OrderBuilder init(Integer customerId){
        this.PSOrder = new PSOrder();
        PSOrder.setStatus(0);
        PSOrder.setEmployeeId(0);
        long createTime = System.currentTimeMillis();
        PSOrder.setCreateTime(createTime);
        PSOrder.setArriveTime(createTime + 604800000);
        PSOrder.setCustomerId(customerId);
        return this;
    }

    public OrderBuilder setItems(CartAPI cart){
        PSOrder.setOrderedItems(cart.getCode());
        PSOrder.setPrice(cart.getCost());
        return this;
    }


    public PSOrder build(){
        String items = PSOrder.getOrderedItems();
        if(!items.equals("")) PSOrder.setOrderedItems(items.substring(0,items.length()-1));
        return PSOrder;
    }

}
