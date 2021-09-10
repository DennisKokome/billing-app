package kokome.billing.bill.api;

import kokome.billing.bill.entity.Bill;
import kokome.billing.bill.service.BillService;
import kokome.billing.enumaration.ProductType;
import kokome.billing.product.entity.Product;
import kokome.billing.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@RestController
@RequestMapping(path = "/api/bill")
@RequiredArgsConstructor
public class BillApi {
    private final BillService billService;

    @PostMapping("/v1/generate/{userId}")
    public ResponseEntity<Bill> generateBillByUserId(@PathVariable Long userId, @RequestBody List<Product> products){
        return ResponseEntity.ok().body(billService.generateBillByUserId(userId, products));
    }

    @GetMapping("/v1/get")
    public ResponseEntity<Bill> getBillByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(billService.getBillByUserId(userId));
    }

}
