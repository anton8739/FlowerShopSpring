package by.yurovski.controller.order;

import by.yurovski.entity.Order;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.enums.OrderItemStatusEnum;
import by.yurovski.enums.PaymentMethodEnum;
import by.yurovski.event.order.OrderEvent;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.OrderService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("guestOrderItemList")
public class OrderConfirmController {
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @PostMapping("/basket/order/confirm")
    public String mainPageGet(@RequestParam String mobphone,
                              @RequestParam String email,
                              @RequestParam String address,
                              @RequestParam String paymentMethod,
                              Model model, Principal principal){
        List<OrderItem> basket;
        Order order;
        if(principal!=null){
            User user =userService.findUserByLogin(principal.getName());
            basket=orderItemService.findAllByUser(user);
            basket.forEach(orderItem ->
            {
                orderItem.setStatus(OrderItemStatusEnum.CONFIRMED);
                orderItemService.saveAndFlush(orderItem);
            });
            order= new Order(basket, mobphone,email,address,PaymentMethodEnum.valueOf(paymentMethod.toUpperCase()),user);
        } else {
            basket=(List<OrderItem>)model.getAttribute("guestOrderItemList");
            basket.forEach(orderItem ->
            {
                orderItem.setStatus(OrderItemStatusEnum.CONFIRMED);
                orderItemService.saveAndFlush(orderItem);
            });
            order= new Order(basket, mobphone,email,address,PaymentMethodEnum.valueOf(paymentMethod.toUpperCase()));
            model.addAttribute("guestOrderItemList", new ArrayList<>());
        }



        order=orderService.saveAndFlush(order);
        eventPublisher.publishEvent(new OrderEvent(order));
        model.addAttribute("orderId",order.getId());
        return "order/orderConfirm.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
