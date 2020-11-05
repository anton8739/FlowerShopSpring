package by.yurovski.controller;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class BasketController {
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/basket")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPageGet(Model model, Principal principal){
        String login = principal.getName();
        User user=userService.findUserByLogin(login);
        List<OrderItem> basket=orderItemService.findAllByUser(user);
        model.addAttribute("userName",user.getLogin());
        model.addAttribute("role",user.getRole().toString());
        model.addAttribute("message", "Hello, master");
        model.addAttribute("basket",basket);

        return "product/basket.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
