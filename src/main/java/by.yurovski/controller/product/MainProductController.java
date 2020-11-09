package by.yurovski.controller.product;

import by.yurovski.dao.ProductDao;
import by.yurovski.entity.Product;
import by.yurovski.service.ProductService;
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

@Controller
public class MainProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{productURL}")
    @PreAuthorize("hasAuthority('product:read')")
    public String mainPageGet(@PathVariable String productURL, Model model, Principal principal){
        Product product=productService.findProductsByURL(productURL);
        model.addAttribute("product", product);
        model.addAttribute("message", "Hello, Anton Yurovski");
        return "product/product.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }

}
