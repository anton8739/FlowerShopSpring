package by.yurovski.event.order;


import by.yurovski.applicationConf.emailConfig.SmtpMailSender;
import by.yurovski.entity.Order;
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
public class OrderListener implements ApplicationListener<OrderEvent> {
    @Autowired
    UserVerificationTokenService userVerificationTokenService;
    @Autowired
    SmtpMailSender smtpMailSender;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    OrderSMSSender orderSMSSender;
    @Value("${server.url}")
    private String path;

    public void onApplicationEvent(OrderEvent event) {
       this.confirmOrder(event);
    }


    public void confirmOrder(OrderEvent event){

        Order order=event.getOrder();
        String recipientAddress = order.getEmail();
        String subject = "Your order номер:"+order.getId();
        Map<String, Object> map= new HashMap<String, Object>();
        if (order.getUser()!=null){
            map.put("userName", order.getUser().getLogin());
        } else {
            map.put("userName", "Гость");
        }
        map.put("orderNumber", order.getId());

        map.put("cost", order.getTotalcost());
        map.put("pay",order.getPaymentmethod());
        orderSMSSender.sendOrderSMS(order);
        try {
            smtpMailSender.sendMessageUsingThymeleafTemplate(recipientAddress,subject,map,"newOrder.html");
        }catch (MessagingException exception){

        }

    }
}
