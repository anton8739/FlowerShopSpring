package by.yurovski.controller.product;

import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AddProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/add" )
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPageGet(Model model, Principal principal){

        return "product/addProduct.html";
    }
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPagePost(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String cost,
                               @RequestParam String url,
                               @RequestParam String category,
                               @RequestParam String availability,
                               @RequestParam String status,
                               Model model, Principal principal){
        Product product= new Product(title,description, Double.parseDouble(cost),
                url,CategoryEnum.valueOf(category),Integer.parseInt(availability), ProductStatusEnum.valueOf(status));
        System.out.println("В сервлете до БД "+product.toString());
        Product productSaved=productService.saveAndFlush(product);
        System.out.println("В сервлете после БД "+productSaved.toString());
        model.addAttribute("message", "Hello, Anton Yurovski");
        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }

}
