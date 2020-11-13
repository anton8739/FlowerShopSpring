package by.yurovski.controller.user;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.enums.OrderItemStatusEnum;
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
import java.util.stream.Collectors;

@Controller
public class BasketController {
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/basket")
    public String mainPageGet(Model model, Principal principal){

        if (principal !=null){
            String login = principal.getName();
            User user=userService.findUserByLogin(login);
            List<OrderItem> basket=orderItemService.findAllByUser(user);
            basket=basket
                    .stream()
                    .filter(orderItem -> orderItem.getStatus().equals(OrderItemStatusEnum.ACTIVE))
                    .collect(Collectors.toList());
            model.addAttribute("userName",user.getLogin());
            model.addAttribute("role",user.getRole().toString());
            model.addAttribute("message", "Hello, master");
            model.addAttribute("basket",basket);

        }

        return "product/basket.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
