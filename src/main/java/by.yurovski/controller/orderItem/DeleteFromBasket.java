package by.yurovski.controller.orderItem;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.Product;
import by.yurovski.entity.User;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.ProductService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DeleteFromBasket {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping(value = "/basket/delete")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPagePost(@RequestParam String id,
                               Model model, Principal principal){

        orderItemService.deleteById(Long.parseLong(id));
        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
