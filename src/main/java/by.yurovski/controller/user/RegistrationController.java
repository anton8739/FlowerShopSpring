package by.yurovski.controller.user;

import by.yurovski.entity.Message;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.event.registration.RegistrationEvent;
import by.yurovski.service.SecurityService;
import by.yurovski.service.UserService;
import by.yurovski.service.UserVerificationTokenService;
import by.yurovski.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    @Qualifier("customPasswordEncoder")
    PasswordEncoder  passwordEncoder;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserVerificationTokenService userVerificationTokenService;
    @Autowired
    RegistrationValidator registrationValidator;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/registration")
    public String mainPageGet(@RequestParam(value = "token", required = false) String token,Model model, Principal principal){
        if (token !=null){
            System.out.println(token);
            UserVerificationToken userVerificationToken=userVerificationTokenService.check(token, new Date());
            if (userVerificationToken != null){
                User user=userVerificationToken.getUser();
                user.setEnabled(true);
                userVerificationTokenService.delete(userVerificationToken);
                user=userService.save(user);
                model.addAttribute("message", "Аккаунт успешно подтвержден, вы можете войти в систему!");
                return "user/login.html";
            }
            model.addAttribute("message", "Токен не верен или просрочен");
            model.addAttribute("userForm", new User());
            return "user/registration.html";
        }

            model.addAttribute("userForm", new User());

        return "user/registration.html";
    }
    @PostMapping("/registration")
    public String mainPagePost(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, Principal principal){
        System.out.println(userForm.toString());
        registrationValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "user/registration.html";
        }
        User user=new User(userForm.getEmail(),userForm.getLogin(),passwordEncoder.encode(userForm.getPassword()));
        user=userService.save(user);
        UserVerificationToken userVerificationToken= new UserVerificationToken(user,new Date());
        userVerificationTokenService.save(userVerificationToken);
        eventPublisher.publishEvent(new RegistrationEvent(user));
        model.addAttribute("message", "На почту отправлено письмо для подтверждения регистрации");
        return "user/login.html";
    }
}
