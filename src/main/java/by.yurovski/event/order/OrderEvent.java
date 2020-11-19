package by.yurovski.event.order;

import by.yurovski.entity.Order;
import by.yurovski.entity.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;


public class OrderEvent extends ApplicationEvent {
    private Order order;
    public OrderEvent(Order order) {
        super(order);
        this.order=order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
