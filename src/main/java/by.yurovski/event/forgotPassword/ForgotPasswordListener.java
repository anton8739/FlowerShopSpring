package by.yurovski.event.forgotPassword;


import by.yurovski.applicationConf.emailConfig.SmtpMailSender;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.event.registration.RegistrationEvent;
import by.yurovski.service.UserVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

@Component
public class ForgotPasswordListener implements ApplicationListener<ForgotPasswordEvent> {
    @Autowired
    UserVerificationTokenService userVerificationTokenService;
    @Autowired
    SmtpMailSender smtpMailSender;
    @Autowired
    private ServletContext servletContext;
    @Value("${server.url}")
    private String path;

    public void onApplicationEvent(ForgotPasswordEvent event) {
       this.confirmPassword(event);
    }


    public void confirmPassword(ForgotPasswordEvent event){

        User user=event.getUser();
        UserVerificationToken userVerificationToken=userVerificationTokenService.findByUser(user);
        String token=userVerificationToken.getToken();
        String recipientAddress = user.getEmail();
        String subject = "Forgot Password";
        String confirmationUrl
                =  path+"/app/forgot?token="+token;
        System.out.println(confirmationUrl);
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("confirmationUrl", confirmationUrl);
        map.put("userName", user.getLogin());
        try {
            smtpMailSender.sendMessageUsingThymeleafTemplate(recipientAddress,subject,map,"forgotPassword.html");
        }catch (MessagingException exception){

        }

    }
}
