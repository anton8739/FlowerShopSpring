package by.yurovski.validator;


import by.yurovski.entity.User;
import by.yurovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator{

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user= (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "email", "Required");
       if (user.getEmail().length() < 4 || user.getEmail().length() > 20) {
            errors.rejectValue("email", "user.email.size");
        }
        if (userService.findUserByEmail(user.getEmail()) !=null){
            errors.rejectValue("email", "user.email.duplicate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "login", "Required");
        if (user.getLogin().length() < 4 || user.getLogin().length() > 20) {
            errors.rejectValue("login", "user.login.size");
        }

       if (userService.findUserByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "user.login.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "user.password.size");
        }
    }
}
