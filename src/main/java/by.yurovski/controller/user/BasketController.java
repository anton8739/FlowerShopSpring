package by.yurovski.controller.user;


import by.yurovski.dto.OrderItemDTO;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import by.yurovski.enums.OrderItemStatusEnum;
import by.yurovski.service.OrderItemService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BasketController {
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public List<OrderItemDTO> mainPageGet(Model model, Principal principal){
        List<OrderItem> basket=null;
        List<OrderItemDTO> orderItemDTOS=null;
        if (principal !=null ){
            String login = principal.getName();
            User user=userService.findUserByLogin(login);
            basket=orderItemService.findAllByUser(user);
            basket=basket
                    .stream()
                    .filter(orderItem -> orderItem.getStatus().equals(OrderItemStatusEnum.ACTIVE))
                    .collect(Collectors.toList());
            if (basket !=null){
                orderItemDTOS = new ArrayList<>();
                for (OrderItem orderItem : basket){
                    OrderItemDTO ord=new OrderItemDTO(orderItem);
                    orderItemDTOS.add(ord);
                    System.out.println(ord.getProductTitle());
                }

                return orderItemDTOS;
            }

        }

        return null;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
