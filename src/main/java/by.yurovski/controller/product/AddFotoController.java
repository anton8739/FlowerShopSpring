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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
public class AddFotoController {
    @Autowired
    ProductService productService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    FotoService fotoService;
    @GetMapping(value = "/foto")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPageGet(@RequestParam String id,Model model, Principal principal) {
        model.addAttribute("product", productService.findProductsById(Integer.parseInt(id)));
        return "product/addFoto.html";
    }

    @PostMapping(value = "/foto")
    @PreAuthorize("hasAuthority('product:add')")
    public String mainPagePost(Model model, Principal principal,
                               @RequestParam("file") MultipartFile[] files,
                               @RequestParam String id) throws IOException {
        String uploadPath = servletContext.getRealPath("/");
        uploadPath=uploadPath.substring(0,uploadPath.length()-2);
        uploadPath=uploadPath
                .substring(0, uploadPath.lastIndexOf('/'))+"/files/";

        for(MultipartFile file :files){
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile  + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

                file.transferTo(new File(uploadPath  + resultFilename));
                Product product=productService.findProductsById(Integer.parseInt(id));
                Foto foto = new Foto(product,resultFilename);
                fotoService.save(foto);

            }
        }

            return "common/main.html";


    }
    @ExceptionHandler(AccessDeniedException.class)
        public String handleAccessDeniedException (AccessDeniedException ex, Model model) throws Exception {

            model.addAttribute("message", "Доступ запрещен всвязи с недостаточностью прав.");
            return "user/errorAccessdenied.html";
        }

}
