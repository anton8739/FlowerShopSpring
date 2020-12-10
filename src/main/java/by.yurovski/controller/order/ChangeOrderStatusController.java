package by.yurovski.controller.order;

import by.yurovski.entity.Order;
import by.yurovski.enums.OrderItemStatusEnum;
import by.yurovski.enums.PaymentStatusEnum;
import by.yurovski.enums.ProgressEnum;
import by.yurovski.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
@Controller
public class ChangeOrderStatusController {
    @Autowired
    OrderService orderService;
    @PostMapping("/order/changeOrderStatus")
    @PreAuthorize("hasAuthority('product:add')")
    public RedirectView mainPagePost(@RequestParam long orderIdChangeOrderStatus,
                                     @RequestParam String orderStatus,
                                     Model model, Principal principal, RedirectAttributes redir){
        orderStatus=orderStatus.replaceAll("[,.]", "");
        System.out.println(orderStatus + "  "+orderIdChangeOrderStatus);
        Order order=orderService.findById(orderIdChangeOrderStatus);
        order.setProgress(ProgressEnum.valueOf(orderStatus));
        orderService.saveAndFlush(order);
        RedirectView redirectView= new RedirectView("/app/orders",true);
        return redirectView;

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
