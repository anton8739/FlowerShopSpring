package by.yurovski.controller.user;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("guestOrderItemList")
public class MainController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;
    @GetMapping("/main")
    public String mainPageGet(Model model, Principal principal){

        if (model.containsAttribute("guestOrderItemList") && principal!=null) {
            List<OrderItem> orderItems=(List<OrderItem>) model.getAttribute("guestOrderItemList");
            User user=userService.findUserByLogin(principal.getName());
            System.out.println();
            for (OrderItem orderItem :orderItems){
                orderItem.setUser(user);
                orderItemService.saveAndFlush(orderItem);
            }
            model.addAttribute("guestOrderItemList", new ArrayList<>());
            System.out.println("из метода1");
        }
        return "common/main.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
