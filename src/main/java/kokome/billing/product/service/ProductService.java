package kokome.billing.product.service;

import kokome.billing.enumaration.ProductType;
import kokome.billing.product.entity.Product;

import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */
public interface ProductService {

    Product saveProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductByProductType(ProductType productType);
}
