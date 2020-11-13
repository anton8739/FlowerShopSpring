package by.yurovski.controller.orderItem;

import by.yurovski.entity.Order;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("guestOrderItemList")
public class DeleteFromBasket {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping(value = "/basket/delete")
    public String mainPagePost(@RequestParam String id,
                               Model model, Principal principal){
        if(principal!=null){
            orderItemService.deleteById(Integer.parseInt(id));
        } else {
            List<OrderItem> orderItems=(ArrayList<OrderItem>)model.getAttribute("guestOrderItemList");
            orderItems=orderItems
                    .stream()
                    .filter(orderItem -> orderItem.getId()!=Integer.parseInt(id))
                    .collect(Collectors.toList());
            model.addAttribute("guestOrderItemList",orderItems);
            orderItemService.deleteById(Integer.parseInt(id));

        }

        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
