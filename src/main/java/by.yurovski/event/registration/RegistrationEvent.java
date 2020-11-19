package by.yurovski.event.registration;

import by.yurovski.entity.User;
import org.springframework.context.ApplicationEvent;


public class RegistrationEvent extends ApplicationEvent {
    private User user;
    public RegistrationEvent(User user) {
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
