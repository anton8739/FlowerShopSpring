package by.yurovski.controller.category;
import by.yurovski.dao.ProductDao;
import by.yurovski.entity.Product;


import by.yurovski.entity.User;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.ProductService;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoryController {

   @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/category/{path}")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPageGet(@PathVariable String path, Model model, Principal principal){
        if (!path.equals("box")
                && !path.equals("occasion")
                && !path.equals("color")
                && !path.equals("composition")
                && !path.equals("gift")){
            return "common/main.html";
        }
        List<Product> products=productService
                .findAllByCategory(CategoryEnum.valueOf(path.toUpperCase()))
                .stream()
                .filter(product -> (product.getStatus().equals(ProductStatusEnum.AVAILABLE)
                        || product.getStatus().equals(ProductStatusEnum.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        String login = principal.getName();
        User user=userService.findUserByLogin(login);
        model.addAttribute("products",products);
        return "common/category.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }

}
