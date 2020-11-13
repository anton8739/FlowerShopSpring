package by.yurovski.controller.orderItem;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.ProductService;
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
public class EditOrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/basket/edit")
    public String mainPageGet(@RequestParam String id, Model model, Principal principal){

        OrderItem orderItem=orderItemService.findById(Integer.parseInt(id));
        model.addAttribute("amount", orderItem.getAmount());
        model.addAttribute("size", orderItem.getSize());
        model.addAttribute("note", orderItem.getNote());
        model.addAttribute("id", id);

        return "product/editOrderItem.html";
    }
    @PostMapping("/basket/edit")
    public String mainPagePost(@RequestParam String id,
                               @RequestParam String amount,
                               @RequestParam String size,
                               @RequestParam String note,
                               Model model, Principal principal){

        OrderItem orderItem=orderItemService.findById(Integer.parseInt(id));
        orderItem.setAmount(Integer.parseInt(amount));
        orderItem.setNote(note);
        orderItem.setSize(size);
        orderItem=orderItemService.saveAndFlush(orderItem);
        if(principal == null){
            OrderItem orderIt=orderItem;
            List<OrderItem> orderItems=(ArrayList<OrderItem>)model.getAttribute("guestOrderItemList");
            orderItems=orderItems
                    .stream()
                    .map(item ->item.getId()==orderIt.getId() ? orderIt : item )
                    .collect(Collectors.toList());
            model.addAttribute("guestOrderItemList",orderItems);
        }
        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
