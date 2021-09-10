package kokome.billing.bill.entity;

import kokome.billing.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @ReadOnlyProperty
    private Long id;

    @ManyToOne
    private Bill bill;

    @ManyToOne
    private Product product;

    private float price;

    public BillProduct(Bill bill, Product product, float price){
        this.bill = bill;
        this.product = product;
        this.price = price;
    }

}
