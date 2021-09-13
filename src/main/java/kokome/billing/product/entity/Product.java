package kokome.billing.product.entity;

import kokome.billing.enumaration.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private float price;
    private ProductType productType;

    public Product(String name, float price, ProductType productType){
        this.name = name;
        this.price = price;
        this.productType = productType;
    }
}
