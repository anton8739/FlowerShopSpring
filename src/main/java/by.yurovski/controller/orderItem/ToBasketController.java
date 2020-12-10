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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("guestOrderItemList")
public class ToBasketController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @PostMapping(value = "/basket/add")
    public RedirectView mainPagePost(@RequestParam String id,
                               @RequestParam String size,
                               @RequestParam String amount,
                               @RequestParam String note,
                               Model model, Principal principal,RedirectAttributes redir){
        Product product=productService.findProductsById(Integer.parseInt(id));
        OrderItem orderItem;
        size=size.replaceAll("[,.]", "");
        System.out.println(size);
        List<OrderItem> guestOrderItemList;
        if(principal!=null){
            User user=userService.findUserByLogin(principal.getName());
            orderItem = new OrderItem(product,user,Integer.parseInt(amount),size,note);
            orderItemService.saveAndFlush(orderItem);
        } else {
            orderItem = new OrderItem(product,Integer.parseInt(amount),size,note);
            orderItem=orderItemService.saveAndFlush(orderItem);
            System.out.println(orderItem.getId());
            if(!model.containsAttribute("guestOrderItemList")){
                guestOrderItemList=new ArrayList<OrderItem>();
                model.addAttribute("guestOrderItemList", guestOrderItemList);
            }
            guestOrderItemList=(List<OrderItem>)model.getAttribute("guestOrderItemList");
            guestOrderItemList.add(orderItem);
        }

        RedirectView redirectView= new RedirectView("/app/product/"+product.getURL(),true);
        redir.addFlashAttribute("Smessage","" +
                "Товар успешно добавлен в корзину!" +
                " Прейти к оформелнию заказа или продолжить покупки!");
        redir.addFlashAttribute("href","href");
        return redirectView;

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
