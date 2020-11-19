package by.yurovski.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="message", schema ="public")
public class Message {
    public Message(){

    }
    public Message(String name, String mobphone, String email, String message){
        this.name=name;
        this.mobphone=mobphone;
        this.email=email;
        this.message=message;
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="mobphone")
    private String mobphone;

    @Column(name="email")
    private String email;

    @Column(name="message")
    private String message;



}
