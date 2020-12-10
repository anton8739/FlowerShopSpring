package by.yurovski.validator;

import by.yurovski.entity.Order;
import by.yurovski.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product= (Product) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cost","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availability","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"URL","Required");
        if(product.getTitle().length() >50){
            errors.rejectValue("title", "product.title.format");
        }
        if(product.getDescription().length() >600){
            errors.rejectValue("description", "product.description.format");
        }
        if(product.getURL().length() >50 && !product.getURL().matches("/^[A-Za-z]+$/")){
            errors.rejectValue("URL", "product.url.format");
        }
    }
}
