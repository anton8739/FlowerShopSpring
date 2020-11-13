package by.yurovski.entity;

import by.yurovski.enums.UserRole;
import lombok.Data;

import javax.persistence.*;

import java.util.List;
@Data
@Entity
@Table(name= "user", schema ="public")
public class User {
    public User(){}
    public User(String login, String password, String email, String role, boolean enabled){
        this.login=login;
        this.password=password;
        this.email=email;
        this.enabled=enabled;
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name= "role")
    private UserRole role;

    @Column(name="enabled")
    private boolean enabled;

    @OneToMany (fetch = FetchType.EAGER,
            mappedBy = "user",
            cascade = CascadeType.REMOVE)
    private List<OrderItem>  orderItems;
    @OneToMany (fetch = FetchType.EAGER,
            mappedBy = "user",
            cascade = CascadeType.REMOVE)
    private List<Order>  orders;
}
