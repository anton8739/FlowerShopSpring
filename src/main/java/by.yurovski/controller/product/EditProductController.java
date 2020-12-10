package by.yurovski.controller.product;

import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.ProductService;
import by.yurovski.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
@Controller
public class EditProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductValidator productValidator;
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPageGet(@RequestParam String id,Model model, Principal principal){
        Product product=productService.findProductsById(Integer.parseInt(id));
        model.addAttribute("title", product.getTitle());
        model.addAttribute("prodForm", product);
        return "product/editProduct.html";
    }
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('product:add')")
    public  String mainPagePost(@ModelAttribute("prodForm") Product prodForm,
                                @RequestParam String category,
                                @RequestParam String status,
                                Model model, Principal principal, BindingResult bindingResult, RedirectAttributes redir){
        category=category.replaceAll("[,.]", "");
        status=status.replaceAll("[,.]", "");
        productValidator.validate(prodForm,bindingResult);
        if(bindingResult.hasErrors()){
            redir.addFlashAttribute("org.springframework.validation.BindingResult.prodForm", bindingResult);
            redir.addFlashAttribute("prodForm", prodForm);
            return "product/editProduct.html";

        }
        Product product=productService.findProductsById(prodForm.getId());
        product.setTitle(prodForm.getTitle());
        product.setDescription(prodForm.getDescription());
        product.setCost(prodForm.getCost());
        product.setURL(prodForm.getURL());
        product.setCategory(CategoryEnum.valueOf(category));
        product.setAvailability(prodForm.getAvailability());
        product.setStatus(ProductStatusEnum.valueOf(status));
        Product productSaved=productService.saveAndFlush(product);
        redir.addFlashAttribute("Smessage","Изменения успешно внесены!");
        return "redirect:/app/product/"+product.getURL();

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
