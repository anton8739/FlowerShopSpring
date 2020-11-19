package by.yurovski.controller.user;

import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.event.forgotPassword.ForgotPasswordEvent;
import by.yurovski.event.registration.RegistrationEvent;
import by.yurovski.service.UserService;
import by.yurovski.service.UserVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
public class ForgotPasswordController {
    @Autowired
    UserService userService;
    @Autowired
    UserVerificationTokenService userVerificationTokenService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    @Qualifier("customPasswordEncoder")
    PasswordEncoder passwordEncoder;
    @GetMapping("/forgot")
    public String mainPageGet(@RequestParam(value = "token", required = false) String token,Model model, Principal principal){
        if (token !=null){
            UserVerificationToken userVerificationToken=userVerificationTokenService.check(token,new Date());
            if(userVerificationToken !=null){

                model.addAttribute("user", userVerificationToken.getUser());
            }
        }
        return "user/forgot.html";
    }
    @PostMapping("/forgot")
    public String mainPagePost(@RequestParam String email, Model model, Principal principal){
        User user=userService.findUserByEmail(email);

        if (user !=null){
            UserVerificationToken userVerificationToken=new UserVerificationToken(user, new Date());
            userVerificationTokenService.save(userVerificationToken);
            eventPublisher.publishEvent(new ForgotPasswordEvent(user));
            model.addAttribute("message", "На почту отправлена ссылка для восстановления пароля");

        } else{
            model.addAttribute("message", "Пользователя с таким email не существует");

        }
        return "user/login.html";
    }
    @PostMapping("/forgot/change")
    public String mainPost(@RequestParam String password, @RequestParam int id, Model model, Principal principal){
        User user=userService.findUserById(id);

        System.out.println(password);
        user.setPassword(passwordEncoder.encode(password));
        UserVerificationToken userVerificationToken=userVerificationTokenService.findByUser(user);
        userVerificationTokenService.delete(userVerificationToken);
        userService.save(user);
        model.addAttribute("message", "Пароль успешно изменен");
        model.addAttribute("userForm", new User());
        return "user/login.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
