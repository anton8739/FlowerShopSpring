package by.yurovski.entity;

import by.yurovski.enums.PaymentMethodEnum;
import by.yurovski.enums.PaymentStatusEnum;
import by.yurovski.enums.ProgressEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="order", schema ="public")
public class Order {
    public Order(){}
    public Order(List<OrderItem> orderItems, String mobphone,
                 String email, String address, PaymentMethodEnum paymentmethod, User user){
        this.orderItems=orderItems;
        this.mobphone=mobphone;
        this.email=email;
        this.address=address;
        this.paymentmethod=paymentmethod;
        this.paymentstatus=PaymentStatusEnum.DONT_PAYED;
        this.progress=ProgressEnum.REGISTERED;
        this.totalcost=0;
        for (OrderItem item :orderItems ){
            this.totalcost+=item.getAmount()*item.getProduct().getCost();
        }
        this.user=user;
    }
    public Order(List<OrderItem> orderItems, String mobphone,
                 String email, String address, PaymentMethodEnum paymentmethod){
        this.orderItems=orderItems;
        this.mobphone=mobphone;
        this.email=email;
        this.address=address;
        this.paymentmethod=paymentmethod;
        this.paymentstatus=PaymentStatusEnum.DONT_PAYED;
        this.progress=ProgressEnum.REGISTERED;
        this.totalcost=0;
        for (OrderItem item :orderItems ){
            this.totalcost+=item.getAmount()*item.getProduct().getCost();
        }
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name="totalcost")
    private double totalcost;

    @Column(name="mobphone")
    private String mobphone;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;
    @Enumerated(value = EnumType.STRING)
    @Column(name="paymentmethod")
    private PaymentMethodEnum paymentmethod;
    @Enumerated(value = EnumType.STRING)
    @Column(name="paymentstatus")
    private PaymentStatusEnum paymentstatus;
    @Enumerated(value = EnumType.STRING)
    @Column(name="progress")
    private ProgressEnum progress;
    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany (fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;
}
