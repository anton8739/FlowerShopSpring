package by.yurovski.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name="userVerificationToken", schema ="public")
public class UserVerificationToken {


    public UserVerificationToken(){}
    public UserVerificationToken(User user, Date date){
        this.user=user;
        this.token=UUID.randomUUID().toString().toUpperCase();
        this.expiryDate=calculateExpiryDate(date);
    }
    @Id
    @GeneratedValue
    private int id;
    @Column(name="token")
    private String token;
    @Column(name="expiryDate")
    private long expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private long calculateExpiryDate(Date date){
        long time=(long) 8.64e5+date.getTime();

        return time;
    }

}
