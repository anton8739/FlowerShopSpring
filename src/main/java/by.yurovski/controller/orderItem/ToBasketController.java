package by.yurovski.controller.orderItem;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.Product;
import by.yurovski.entity.User;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.ProductService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
public class ToBasketController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @PostMapping(value = "/basket/add")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPagePost(@RequestParam String id,
                               @RequestParam String size,
                               @RequestParam String amount,
                               @RequestParam String note,
                               Model model, Principal principal){

        Product product=productService.findProductsById(Integer.parseInt(id));
        User user=userService.findUserByLogin(principal.getName());
        OrderItem orderItem = new OrderItem(product,user,Integer.parseInt(amount),size,note);
        orderItemService.saveAndFlush(orderItem);

        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
