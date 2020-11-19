package by.yurovski.event.forgotPassword;

import by.yurovski.entity.User;
import org.springframework.context.ApplicationEvent;


public class ForgotPasswordEvent extends ApplicationEvent {
    private User user;
    public ForgotPasswordEvent(User user) {
        super(user);
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
