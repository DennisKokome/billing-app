package kokome.billing.bill.service.impl;

import kokome.billing.profile.entity.User;
import kokome.billing.profile.repository.ProfileRepository;
import kokome.billing.bill.entity.Bill;
import kokome.billing.bill.entity.BillProduct;
import kokome.billing.bill.repository.BillProductRepository;
import kokome.billing.bill.repository.BillRepository;
import kokome.billing.bill.service.BillService;
import kokome.billing.product.entity.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static kokome.billing.enumaration.BillStatus.NOT_PAID;
import static kokome.billing.util.KnownRoles.CUSTOMER;

/**
 * Created by oaitse on Sep, 09, 2021
 */

@Service
@Transactional
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private BillProductRepository billProductRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, BillProductRepository billProductRepository,
                           ProfileRepository profileRepository){
        this.billRepository = billRepository;
        this.billProductRepository = billProductRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Bill getBillByUserId(Long id) {
        return billRepository.findByUser_Id(id);
    }

    @Override
    public Bill generateBillByUserId(Long userId, List<Product> products) {

        User user = profileRepository.findUserById(userId);
        Bill bill = new Bill();
        bill.setInvoiceNumber(generateInvoiceNumber());
        bill.setUser(user);
        bill.setBillStatus(NOT_PAID);
        bill.setDiscount(getPercentageDiscount(user));
        bill.setIssued(LocalDate.now());

        Float fullAmount = 0F;
        for (Product product : products) {
            fullAmount = fullAmount + product.getPrice();
        }

        bill.setFullAmount(fullAmount);
        bill.setDiscountedAmount(0F);

        Float amountBasedDiscount = applyAmountBasedDiscount(fullAmount, 5F);
        List<Product> groceryProducts = products.stream()
                .filter(product -> product.getProductType().name().equals("GROCERY"))
                .collect(Collectors.toList());

        Float groceryAmount = 0F;
        for (Product product : groceryProducts) {
            groceryAmount = groceryAmount + product.getPrice();
        }

        Float fullyDiscountedBill = applyPercentageBasedDiscount(amountBasedDiscount, groceryAmount,  bill.getDiscount());
        bill.setDiscountedAmount(fullyDiscountedBill);
        Bill savedBill = billRepository.save(bill);

        BillProduct billProduct;
        products.stream()
                .forEach(product -> billProductRepository.save(new BillProduct(savedBill, product, product.getPrice())));

        return savedBill;
    }

    public String generateInvoiceNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public Float getPercentageDiscount(User user){
        if(user.getRole().getName().equals(CUSTOMER)){
            LocalDate today = LocalDate.now();
            if (user.getJoinDate().isBefore(today.minusYears(2))) {
                return user.getRole().getDiscount();
            }else return 0F;
        }
        return user.getRole().getDiscount();
    }

    public Float applyAmountBasedDiscount(Float billAmount, Float discountAmount){
        if(billAmount > 100) {
            int multiplier = (int) (billAmount / 100);

            if (multiplier < 1)
                return billAmount;

            return billAmount - (discountAmount * multiplier);
        }
        return billAmount;
    }

    public Float applyPercentageBasedDiscount(Float billAmount, Float groceryAmount, Float discountPercentage){
        if(discountPercentage > 0) {
            billAmount = billAmount - groceryAmount;
            return (billAmount * ((100 - discountPercentage) / 100)) + groceryAmount;
        }
        return billAmount;
    }
}
