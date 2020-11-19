package by.yurovski.controller.user;


import by.yurovski.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Locale;

@Controller
public class AuthController {
    @Autowired
    MessageSource messageSource;
    @GetMapping("/login")
    public String loginPageGet(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, Locale locale){
        if (error != null) {
            model.addAttribute("message", messageSource.getMessage("user.login.error", null, locale));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "common/main.html";
        }
        return "user/login.html";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
