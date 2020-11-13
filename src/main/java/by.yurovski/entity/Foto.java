package by.yurovski.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="foto", schema ="public")
public class Foto {
    public Foto(){}

    public Foto(Product product, String URL){
        this.prod=product;
        this.URL=URL;
    }
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name="url")
    private String URL;

    @ManyToOne( fetch=FetchType.LAZY)
    @JoinColumn(name="prod_id")
    private Product prod;

}
