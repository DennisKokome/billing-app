package kokome.billing.bill.repository;

import kokome.billing.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oaitse on Sep, 09, 2021
 */
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByUser_Id(Long userId);
}
