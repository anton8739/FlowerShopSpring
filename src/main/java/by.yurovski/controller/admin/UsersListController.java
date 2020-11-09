package by.yurovski.controller.admin;

import by.yurovski.entity.Product;
import by.yurovski.entity.User;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersListController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('product:edit')")
    public String mainPageGet(Model model, Principal principal){
        List<User> userList=userService.findAll();
        model.addAttribute("users",userList);
        return "admin/userList.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
