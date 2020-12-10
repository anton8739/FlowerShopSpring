package by.yurovski.controller.product;

import by.yurovski.entity.Foto;
import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import by.yurovski.service.FotoService;
import by.yurovski.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class DeleteFotoController  {
    @Autowired
    ProductService productService;
    @Autowired
    FotoService fotoService;
    @PostMapping(value = "/product/deleteFoto")
    @PreAuthorize("hasAuthority('product:add')")
    public RedirectView mainPagePost(@RequestParam("fotos")long[] fotos, @RequestParam int prodId, Model model, Principal principal, RedirectAttributes redir){
        Product product=productService.findProductsById(prodId);
        for(long id : fotos){
            Foto foto=fotoService.findById(id);

            fotoService.delete(foto);
        }

        RedirectView redirectView= new RedirectView("/app/product/"+product.getURL(),true);
        redir.addFlashAttribute("Smessage","Фотографии успешно удалены");
        return redirectView;

    }
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex,Model model) throws Exception {

        model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
        return "user/errorAccessdenied.html";
    }
}
