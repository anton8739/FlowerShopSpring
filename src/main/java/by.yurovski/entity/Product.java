package by.yurovski.entity;

import by.yurovski.enums.CategoryEnum;
import by.yurovski.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="product", schema ="public")
public class Product {
    public Product(){

    }
    public Product(String title, String description, double cost, String url, CategoryEnum category, int availability, ProductStatusEnum productStatusEnum){
        this.title=title;
        this.description=description;
        this.cost=cost;
        this.category=category;
        this.URL=url;
        this.availability=availability;
        this.status=productStatusEnum;
    }
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="cost")
    private double cost;


    @Enumerated(value = EnumType.STRING)
    @Column(name="category")
    private CategoryEnum category;

    @Column(name="url")
    private String URL;

    @Column(name="availability")
    private int availability;
    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private ProductStatusEnum status;

    @OneToMany (fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = CascadeType.REMOVE)
    private List<OrderItem>  orderItems;

    @OneToMany (fetch = FetchType.EAGER,
            mappedBy = "prod",
            cascade = CascadeType.REMOVE)
    private List<Foto>  fotos;

}
