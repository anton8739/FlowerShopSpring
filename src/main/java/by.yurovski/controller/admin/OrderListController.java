package by.yurovski.controller.admin;

import by.yurovski.entity.Order;
import by.yurovski.entity.Product;
import by.yurovski.service.OrderService;
import by.yurovski.service.ProductService;
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
public class OrderListController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('product:edit')")
    public String mainPageGet(Model model, Principal principal){
        List<Order> orders=orderService.findAll();
        model.addAttribute("orders",orders);
        return "admin/orderList.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
