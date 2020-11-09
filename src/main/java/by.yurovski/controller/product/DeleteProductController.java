package by.yurovski.controller.product;

import by.yurovski.entity.Product;
import by.yurovski.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DeleteProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('product:delete')")
    public String mainPageGet(@RequestParam String id, Model model, Principal principal){
        Product product = productService.findProductsById(Integer.parseInt(id));
        productService.delete(product);
        return "common/main.html";
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
