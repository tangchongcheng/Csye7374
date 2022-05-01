package edu.neu.csye7374.facade;

import edu.neu.csye7374.entity.PSOrder;
import edu.neu.csye7374.entity.item.*;
import edu.neu.csye7374.facade.cart.*;
import edu.neu.csye7374.facade.cart.game.EldenRingAdd;
import edu.neu.csye7374.facade.cart.game.GameAPI;
import edu.neu.csye7374.facade.cart.game.Persona5Add;
import edu.neu.csye7374.facade.order.OrderBuilder;

public class CartFacade {
    CartAPI cart;
    PSOrder PSOrder;
    OrderBuilder orderBuilder;

    public CartFacade(){
        cart = new Cart();
        orderBuilder = new OrderBuilder();
        PSOrder = orderBuilder.init().setItems(cart).build();
    }

    public void addController(Controller controller){
        cart = new ControllerAdd(cart, controller);
        PSOrder = orderBuilder.setItems(cart).build();
    }

    public void addMonitor(Monitor monitor){
        cart = new MonitorAdd(cart, monitor);
        PSOrder = orderBuilder.setItems(cart).build();
    }

    public void addPlayStation(PlayStation playStation){
        cart = new PlayStationAdd(cart, playStation);
        PSOrder = orderBuilder.setItems(cart).build();
    }

    public void addEldenRing(EldenRing eldenRing){
        GameAPI eldenRingAdd = new EldenRingAdd(eldenRing);
        cart = new GameAdaptor(cart, eldenRingAdd);
        PSOrder = orderBuilder.setItems(cart).build();
    }

    public void addPersona5(Persona5 persona5){
        GameAPI persona5Add = new Persona5Add(persona5);
        cart = new GameAdaptor(cart, persona5Add);
        PSOrder = orderBuilder.setItems(cart).build();
    }

    public PSOrder getOrder(){
        String items = PSOrder.getOrderedItems();
        if(!items.equals("")) PSOrder.setOrderedItems(items.substring(0,items.length()-1));
        return PSOrder;
    }
}
