package kokome.billing.bill.service;

import kokome.billing.bill.entity.Bill;
import kokome.billing.product.entity.Product;

import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */
public interface BillService {
    Bill getBillByUserId(Long id);
    Bill generateBillByUserId(Long userId, List<Product> products);
}
