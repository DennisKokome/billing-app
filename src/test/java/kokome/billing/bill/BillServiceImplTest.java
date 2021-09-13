package kokome.billing.bill;

/**
 * Created by oaitse on Sep, 10, 2021
 */

import kokome.billing.profile.entity.User;
import kokome.billing.profile.repository.ProfileRepository;
import kokome.billing.role.entity.Role;
import kokome.billing.bill.entity.Bill;
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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    private ProfileRepository profileRepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Test
    public void testGenerateBill(){
        Product headPhone = new Product(1L, "Bose HeadPhones",3750.60F, ELECTRONIC);
        Product milk = new Product(2L, "Milk",10.60F, GROCERY);
        Product shirt = new Product(3L, "Shirt",70.60F, CLOTHING);
        Product shoes = new Product(4L, "Shoes",150.60F, CLOTHING);

        List<Product> products = List.of(headPhone, milk, shirt, shoes);

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
        bill.setPercentageDiscount(billService.getPercentageDiscount(user));
        bill.setIssued(LocalDate.now());

        HashMap<String, Float> productMap = new HashMap<>();

        products.stream()
                .forEach(product -> productMap.put(product.getName(), product.getPrice()));
        bill.setProducts(productMap);

        bill.setFullAmount(3982.40F);
        bill.setDiscountedAmount(0F);

        Float amountBasedDiscount = billService.applyAmountBasedDiscount(3982.40F, 5F);

        //get grocery products
        List<Product> groceryProducts = products.stream()
                .filter(product -> product.getProductType().name().equals("GROCERY"))
                .collect(Collectors.toList());

        Float groceryAmount = 0F;
        for (Product product : groceryProducts) {
            groceryAmount = groceryAmount + product.getPrice();
        }

        Float fullyDiscountedBill = billService.applyPercentageBasedDiscount(amountBasedDiscount, 10.60F, bill.getPercentageDiscount());
        bill.setDiscountedAmount(fullyDiscountedBill);


        when(billRepository.save(bill)).thenReturn(bill);
        Bill generatedBill = billRepository.save(bill);

        Assert.assertNotNull(generatedBill.getId());
        Assert.assertEquals(bill, generatedBill);
        verify(billRepository).save(bill);
    }
}
