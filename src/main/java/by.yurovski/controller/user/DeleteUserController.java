package by.yurovski.controller.user;

import by.yurovski.entity.Message;
import by.yurovski.entity.User;
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
public class DeleteUserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/delete")
    @PreAuthorize("isAuthenticated()")
    public RedirectView mainPagePost(@RequestParam String userName,
                                     Model model, Principal principal, RedirectAttributes redir){
        User user=userService.findUserByLogin(userName);
        userService.delete(user);
        if (principal.getName().equals(userName)) {
            SecurityContextHolder.clearContext();

        }

        RedirectView redirectView= new RedirectView("/app/main",true);
        redir.addFlashAttribute("Smessage","Ваш аккаунт успешно удален!");

        return redirectView;
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
