package by.yurovski.controller.admin;

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

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@Controller
public class ProductListController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('product:edit')")
    public String mainPageGet( Model model, Principal principal){

        List<Product> products=productService.findAll();
        model.addAttribute("products",products);
        return "admin/productList.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
