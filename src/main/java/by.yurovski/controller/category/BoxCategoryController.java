package by.yurovski.controller.category;
import by.yurovski.dao.ProductDao;
import by.yurovski.entity.Product;


import by.yurovski.entity.User;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.service.ProductService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class BoxCategoryController {

   @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/category/box")
    @PreAuthorize("hasAuthority('product:edit')")
    public String mainPageGet(Model model, Principal principal){
        List<Product> products=productService.findAllByCategory(CategoryEnum.BOX);
        String login = principal.getName();
        User user=userService.findUserByLogin(login);
        model.addAttribute("products",products);
        model.addAttribute("userName",user.getLogin());
        model.addAttribute("role",user.getRole().toString());
        model.addAttribute("message", "Hello, master");
        return "common/box.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }

}
