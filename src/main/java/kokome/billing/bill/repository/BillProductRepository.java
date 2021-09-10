package kokome.billing.bill.repository;

import kokome.billing.bill.entity.Bill;
import kokome.billing.bill.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */
public interface BillProductRepository extends JpaRepository<BillProduct, Long> {
    Bill findByBill_Id(Long userId);
    List<BillProduct> findBillProductByBill_Id(Long billId);
}
