package by.yurovski.event.registration;


import by.yurovski.applicationConf.emailConfig.SmtpMailSender;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.service.UserVerificationTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {
    @Autowired
    UserVerificationTokenService userVerificationTokenService;
    @Autowired
    SmtpMailSender smtpMailSender;
    @Autowired
    private ServletContext servletContext;
    @Value("${server.url}")
    private String path;

    public void onApplicationEvent(RegistrationEvent event) {
       this.confirmRegistration(event);
    }


    public void confirmRegistration(RegistrationEvent event){

        User user=event.getUser();
        UserVerificationToken userVerificationToken=userVerificationTokenService.findByUser(user);
        String token=userVerificationToken.getToken();
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                =  path+"/app/registration?token="+token;
        System.out.println(confirmationUrl);
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("confirmationUrl", confirmationUrl);
        map.put("userName", user.getLogin());
        try {
            smtpMailSender.sendMessageUsingThymeleafTemplate(recipientAddress,subject,map,"newUser.html");
        }catch (MessagingException exception){

        }

    }
}
