package kokome.billing.product.service.impl;

import kokome.billing.enumaration.ProductType;
import kokome.billing.product.entity.Product;
import kokome.billing.product.repository.ProductRepository;
import kokome.billing.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByProductType(ProductType productType) {
        return productRepository.findProductByProductType(productType);
    }
}
