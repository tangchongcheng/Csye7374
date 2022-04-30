package edu.neu.csye7374.facade;

import edu.neu.csye7374.entity.Order;
import edu.neu.csye7374.entity.item.*;
import edu.neu.csye7374.facade.cart.*;
import edu.neu.csye7374.facade.cart.game.EldenRingAdd;
import edu.neu.csye7374.facade.cart.game.GameAPI;
import edu.neu.csye7374.facade.cart.game.Persona5Add;
import edu.neu.csye7374.facade.order.OrderBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class CartFacade {
    CartAPI cart;
    Order order;
    OrderBuilder orderBuilder;

    public CartFacade(){
        cart = new Cart();
        orderBuilder = new OrderBuilder();
        order = orderBuilder.init().setItems(cart).build();
    }

    public void addController(Controller controller){
        cart = new ControllerAdd(cart, controller);
        order = orderBuilder.setItems(cart).build();
    }

    public void addMonitor(Monitor monitor){
        cart = new MonitorAdd(cart, monitor);
        order = orderBuilder.setItems(cart).build();
    }

    public void addPlayStation(PlayStation playStation){
        cart = new PlayStationAdd(cart, playStation);
        order = orderBuilder.setItems(cart).build();
    }

    public void addEldenRing(EldenRing eldenRing){
        GameAPI eldenRingAdd = new EldenRingAdd(eldenRing);
        cart = new GameAdaptor(cart, eldenRingAdd);
        order = orderBuilder.setItems(cart).build();
    }

    public void addPersona5(Persona5 persona5){
        GameAPI persona5Add = new Persona5Add(persona5);
        cart = new GameAdaptor(cart, persona5Add);
        order = orderBuilder.setItems(cart).build();
    }

    public Order getOrder(){
        return order;
    }
}
