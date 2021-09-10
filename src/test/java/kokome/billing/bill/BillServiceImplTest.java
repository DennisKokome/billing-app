package kokome.billing.bill;

/**
 * Created by oaitse on Sep, 10, 2021
 */

import kokome.billing.profile.entity.User;
import kokome.billing.profile.repository.ProfileRepository;
import kokome.billing.role.entity.Role;
import kokome.billing.bill.entity.Bill;
import kokome.billing.bill.repository.BillProductRepository;
import kokome.billing.bill.repository.BillRepository;
import kokome.billing.bill.service.impl.BillServiceImpl;
import kokome.billing.product.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static kokome.billing.enumaration.BillStatus.NOT_PAID;
import static kokome.billing.enumaration.ProductType.*;
import static kokome.billing.enumaration.ProductType.CLOTHING;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private BillProductRepository billProductRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Test
    public void testGenerateBill(){
        Product product = new Product(1L, "Bose HeadPhones",3750.60F, ELECTRONIC);
        Product product1 = new Product(2L, "Milk",10.60F, GROCERY);
        Product product2 = new Product(3L, "Shirt",70.60F, CLOTHING);
        Product product3 = new Product(4L, "Shoes",150.60F, CLOTHING);

        List<Product> products = List.of(product, product1, product2, product3);

        User user = new User(1L,
                "Oaitse, ",
                "Kokome",
                "75167171",
                "kokomeodk@gmail.com",
                "kokomeodk@gmail.com",
                "kokome",
                new Role(1L,"CUSTMER", 30F),
                LocalDate.of(2021,9,9));
        profileRepository.save(user);
        when(profileRepository.findUserById(user.getId())).thenReturn(user);

        Bill bill = new Bill();
        bill.setId(1L);
        bill.setUser(user);
        bill.setInvoiceNumber(billService.generateInvoiceNumber());
        bill.setUser(user);
        bill.setBillStatus(NOT_PAID);
        bill.setDiscount(billService.getPercentageDiscount(user));
        bill.setIssued(LocalDate.now());
        bill.setFullAmount(3982.40F);
        bill.setDiscountedAmount(0F);

        Float amountBasedDiscount = billService.applyAmountBasedDiscount(3982.40F, 5F);
        Float fullyDiscountedBill = billService.applyPercentageBasedDiscount(amountBasedDiscount, 10.60F, bill.getDiscount());
        bill.setDiscountedAmount(fullyDiscountedBill);

        when(billRepository.save(bill)).thenReturn(bill);


//        BillProduct billProduct;
//        products.stream()
//                .forEach(product -> billProductRepository.save(new BillProduct(1L, bill, product, product.getPrice())));
//
//        BillProduct billProduct = new BillProduct();
//        when(billProductRepository.save(billProduct)).thenReturn(billProduct);

        Bill savedBill = billService.generateBillByUserId(user.getId(), products);

        System.out.println(bill.getUser().getUsername());
        System.out.println(savedBill.getUser().getUsername());

        System.out.println(bill.getId());
        System.out.println(savedBill.getId());

        Assert.assertEquals(bill, savedBill);
        verify(billRepository).save(bill);
//        verify(billProductRepository).save(billProduct);
    }
}
