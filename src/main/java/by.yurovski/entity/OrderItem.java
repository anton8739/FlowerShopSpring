package by.yurovski.entity;

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
    }

    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name="amount")
    private int amount;

    @Column(name="size")
    private String size;

    @Column(name="note")
    private String note;

    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="prod_id")
    private Product product;
    @ManyToOne( fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

}
