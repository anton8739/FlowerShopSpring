package by.yurovski.controller.user;

import by.yurovski.dao.MessageDao;
import by.yurovski.entity.Message;
import by.yurovski.entity.Order;
import by.yurovski.entity.User;
import by.yurovski.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    MessageService messageService;

    @GetMapping("/contacts")
    public String mainPageGet(Model model, Principal principal){
        if(principal!=null){
            model.addAttribute("userName", principal.getName());
            model.addAttribute("messagesList", messageService.findAll());

        } else {
            model.addAttribute("userName", "Гость");
        }

        return "user/contacts.html";
    }
    @PostMapping("/contacts")
    public RedirectView mainPagePost(@RequestParam String name,
                                     @RequestParam String mobphone, @RequestParam String email, @RequestParam String message,
                                     Model model, Principal principal, RedirectAttributes redir){
        System.out.println(name+" "+mobphone+" "+email+" "+message);
        Message mess= new Message(name,mobphone,email,message);
        mess=messageService.save(mess);
        RedirectView redirectView= new RedirectView("/app/main",true);
        redir.addFlashAttribute("Smessage","Ваше обращение успешно зарегистрировано, ожидайте ответа на электронную почту!");

        return redirectView;
    }

}
