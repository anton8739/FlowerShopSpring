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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
@Controller
public class EditProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPageGet(@RequestParam String id,Model model, Principal principal){
        Product product=productService.findProductsById(Integer.parseInt(id));
        model.addAttribute("title", product.getTitle());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("cost", product.getCost());
        model.addAttribute("url", product.getURL());
        model.addAttribute("category", product.getCategory());
        model.addAttribute("foto", " ");
        model.addAttribute("availability",product.getAvailability());
        model.addAttribute("status",product.getStatus());
        model.addAttribute("id", id);
        return "product/editProduct.html";
    }
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPagePost(@RequestParam String id,
                               @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String cost,
                               @RequestParam String url,
                               @RequestParam String category,
                               @RequestParam String availability,
                               @RequestParam String status,
                               Model model, Principal principal){
        Product product=productService.findProductsById(Integer.parseInt(id));
        product.setTitle(title);
        product.setDescription(description);
        product.setCost(Double.parseDouble(cost));
        product.setURL(url);
        product.setCategory(CategoryEnum.valueOf(category));
        product.setAvailability(Integer.parseInt(availability));
        product.setStatus(ProductStatusEnum.valueOf(status));
        Product productSaved=productService.saveAndFlush(product);
        model.addAttribute("message", "Hello, Anton Yurovski");
        return "common/main.html";

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
