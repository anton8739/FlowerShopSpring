package by.yurovski.controller.user;

import by.yurovski.entity.Order;
import by.yurovski.entity.User;
import by.yurovski.service.OrderService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserOrderListController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/user/orders")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPageGet(Model model, Principal principal){
        User user=userService.findUserByLogin(principal.getName());
        List<Order> orders=orderService.findAllByUser(user);
        model.addAttribute("orders",orders);
        return "user/orderList.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
