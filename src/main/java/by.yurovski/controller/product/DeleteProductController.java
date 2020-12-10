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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class DeleteProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('product:delete')")
    public RedirectView mainPageGet(@RequestParam String productId, Model model, Principal principal, RedirectAttributes redir){
        Product product = productService.findProductsById(Integer.parseInt(productId));
        productService.delete(product);
        RedirectView redirectView= new RedirectView("/app/category/"+product.getCategory().toString().toLowerCase(),true);
        redir.addFlashAttribute("Smessage","Товар "+product.getTitle()+" успешно удален!");
        return redirectView;
    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
