package kokome.billing.bill.entity;

import kokome.billing.profile.entity.User;
import kokome.billing.enumaration.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @ReadOnlyProperty
    private Long id;

    private String invoiceNumber;

    @ManyToOne
    private User user;
    private float fullAmount;
    private float discountedAmount;
    private float discount;
    private LocalDate issued;
    private BillStatus billStatus;
//    private List<BillProduct> products;
}
