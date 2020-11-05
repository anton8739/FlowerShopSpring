package by.yurovski.entity;

import by.yurovski.enums.CategoryEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="product", schema ="public")
public class Product {
    public Product(){

    }
    public Product(String title, String description, double cost, String foto, String url){
        this.title=title;
        this.description=description;
        this.cost=cost;
        this.foto=foto;
        this.URL=url;
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="cost")
    private double cost;

    @Column(name="foto")
    private String foto;
    @Enumerated(value = EnumType.STRING)
    @Column(name="category")
    private CategoryEnum category;

    @Column(name="url")
    private String URL;

    @OneToMany (fetch = FetchType.EAGER,
            mappedBy = "product",
            cascade = CascadeType.ALL)
    private List<OrderItem>  orderItems;


}
