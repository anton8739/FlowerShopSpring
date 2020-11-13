package by.yurovski.event;

import by.yurovski.entity.Order;
import by.yurovski.entity.OrderItem;
import by.yurovski.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;


@Component
public class SessionEndedListener implements ApplicationListener<SessionDestroyedEvent> {
    @Autowired
    OrderItemService orderItemService;
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        HttpSessionDestroyedEvent httpevent=(HttpSessionDestroyedEvent) event;
        List<SecurityContext> lstSecurityContext =
                event.getSecurityContexts();
        UserDetails ud;
        List<OrderItem> orderItems=( List<OrderItem> ) httpevent.getSession().getAttribute("guestOrderItemList");
        if (orderItems != null && !orderItems.isEmpty()){
            for (OrderItem orderItem :orderItems){
                orderItem=orderItemService.findById(orderItem.getId());
             if (orderItem.getStatus().toString().equals("ACTIVE")){
                    orderItemService.deleteById(orderItem.getId());
              }
            }

        }
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails)
                    securityContext.getAuthentication().getPrincipal();
                System.out.println("Logout|Session destroyed User: [{}]"+
                        ud.getUsername());

        }
    }

}
