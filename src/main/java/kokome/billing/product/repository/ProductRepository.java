package kokome.billing.product.repository;

import kokome.billing.enumaration.ProductType;
import kokome.billing.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByProductType(ProductType productType);
}
