package by.yurovski.controller.order;


import by.yurovski.entity.Order;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.enums.OrderItemStatusEnum;
import by.yurovski.enums.PaymentMethodEnum;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.OrderService;
import by.yurovski.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/basket/order")
    public String mainPageGet(Model model){
        model.addAttribute("orderForm", new Order());
        return "order/order.html";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");

        return "user/errorAccessdenied.html";
    }
}
