package by.yurovski.event.order;
import by.yurovski.entity.Order;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class OrderSMSSender {


    public static final String ACCOUNT_SID = "ACffe6cffb22735be94d33da1f4aced659";
    public static final String AUTH_TOKEN = "e62ab8c2cb2786e9fddfd17642774a7b";
    public void sendOrderSMS(Order order) {
        String SMS_BODY;
        if (order.getUser() !=null){
            SMS_BODY="Уважаемый "+order.getUser().getLogin()+
                    "! Ваш заказ №"+order.getId()+" успешно оформлен! Ожидайте звонка консультанта!";
        } else {
            SMS_BODY="Уважаемый Гость! "+
                    "Ваш заказ №"+order.getId()+" успешно оформлен! Ожидайте звонка консультанта!";
        }
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(order.getMobphone()),
                new com.twilio.type.PhoneNumber("+13614912607"),
                SMS_BODY)
                .create();
    }
}
