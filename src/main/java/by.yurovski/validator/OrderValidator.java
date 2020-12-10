package by.yurovski.validator;

import by.yurovski.entity.Order;
import by.yurovski.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order= (Order) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mobphone","Required");

        if( !order.getMobphone().isEmpty() && !order.getMobphone().matches("^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$")){
            errors.rejectValue("mobphone", "order.mobphone.format");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "email", "Required");
        if (order.getEmail().length() < 4 || order.getEmail().length() > 20) {
            errors.rejectValue("email", "order.email.size");
        }
    }
}
