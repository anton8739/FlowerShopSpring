package by.yurovski.entity;

import by.yurovski.enums.OrderItemStatusEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="orderitem", schema ="public")
public class OrderItem {
    public OrderItem(){}

    public OrderItem(Product product, User user, int amount, String size, String note){
        this.product=product;
        this.user=user;
        this.amount=amount;
        this.size=size;
        this.note=note;
        this.status=OrderItemStatusEnum.ACTIVE;
    }
    public OrderItem(Product product, int amount, String size, String note){
        this.product=product;
        this.amount=amount;
        this.size=size;
        this.note=note;
        this.status=OrderItemStatusEnum.ACTIVE;
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="amount")
    private int amount;

    @Column(name="size")
    private String size;

    @Column(name="note")
    private String note;
    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private OrderItemStatusEnum status;

    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="prod_id")
    private Product product;

    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;

}
