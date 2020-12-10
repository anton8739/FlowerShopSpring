package by.yurovski.controller.user;

import by.yurovski.entity.User;
import by.yurovski.enums.UserRole;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ChangeUserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/change")
    @PreAuthorize("hasAuthority('product:add')")
    public RedirectView mainPagePost(@RequestParam String userNameChangeRole, @RequestParam String role,
                                     Model model, Principal principal, RedirectAttributes redir){
        role=role.replaceAll("[,.]", "");
        User user=userService.findUserByLogin(userNameChangeRole);
        user.setRole(UserRole.valueOf(role));
        userService.save(user);
        RedirectView redirectView= new RedirectView("/app/users",true);
        redir.addFlashAttribute("Smessage","Роль пользователя изменена!");

        return redirectView;
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}

