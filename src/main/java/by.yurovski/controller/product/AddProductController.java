package by.yurovski.controller.product;

import by.yurovski.entity.Product;
import by.yurovski.entity.User;
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
public class AddProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductValidator productValidator;

    @GetMapping(value = "/add" )
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPageGet(Model model, Principal principal){
        model.addAttribute("prodForm", new Product());
        return "product/addProduct.html";
    }
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPagePost(@ModelAttribute("prodForm") Product prodForm, BindingResult bindingResult,
                               @RequestParam String category,
                               @RequestParam String status,
                               Model model, Principal principal, RedirectAttributes redir){
        category=category.replaceAll("[,.]", "");
        status=status.replaceAll("[,.]", "");
        productValidator.validate(prodForm,bindingResult);
        if(bindingResult.hasErrors()){
            redir.addFlashAttribute("org.springframework.validation.BindingResult.prodForm", bindingResult);
            redir.addFlashAttribute("prodForm", prodForm);
            return "product/addProduct.html";

        }
        Product product= new Product(prodForm.getTitle(),prodForm.getDescription(), prodForm.getCost(),
                prodForm.getURL(),CategoryEnum.valueOf(category),prodForm.getAvailability(), ProductStatusEnum.valueOf(status));
        System.out.println("В сервлете до БД "+product.toString());
        Product productSaved=productService.saveAndFlush(product);
        System.out.println("В сервлете после БД "+productSaved.toString());
        redir.addFlashAttribute("Smessage","Товар успешно создан!");
        return "redirect:/app/product/"+product.getURL();

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }

}
