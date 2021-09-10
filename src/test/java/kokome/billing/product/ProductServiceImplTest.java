package kokome.billing.product;

import kokome.billing.product.entity.Product;
import kokome.billing.product.repository.ProductRepository;
import kokome.billing.product.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static kokome.billing.enumaration.ProductType.*;
import static org.mockito.Mockito.*;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testSaveProduct(){
        Product product = new Product(1L, "Bose HeadPhones",3750.60F, ELECTRONIC);

        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.saveProduct(product);

        Assert.assertNotNull(savedProduct);
        Assert.assertEquals(product, savedProduct);
        Assert.assertEquals(product.getName(), savedProduct.getName());
        verify(productRepository).save(product);
    }

    @Test
    public void testGetAllProducts(){

        Product product = new Product(1L, "Bose HeadPhones",3750.60F, ELECTRONIC);
        Product product1 = new Product(2L, "Milk",10.60F, GROCERY);
        Product product2 = new Product(3L, "Shirt",70.60F, CLOTHING);
        Product product3 = new Product(4L, "Shoes",150.60F, CLOTHING);

        List<Product> products = new ArrayList<>();
        products.addAll(List.of(product, product1, product2, product3));

        when(productRepository.findAll()).thenReturn(products);
        List<Product> savedProducts = productService.getAllProducts();

        Assert.assertNotNull(savedProducts);
        Assert.assertEquals(products, savedProducts);
        verify(productRepository).findAll();
    }

    @Test
    public void testGetProductByProductType(){
        Product product = new Product(1L, "Bose HeadPhones",3750.60F, ELECTRONIC);
        Product product1 = new Product(2L, "Milk",10.60F, GROCERY);
        Product product2 = new Product(3L, "Shirt",70.60F, CLOTHING);
        Product product3 = new Product(4L, "Shoes",150.60F, CLOTHING);

        List<Product> clothingProducts = new ArrayList<>();
        clothingProducts.addAll(List.of(product2, product3));

        when(productRepository.findProductByProductType(CLOTHING)).thenReturn(clothingProducts);
        List<Product> savedProducts = productService.getProductByProductType(CLOTHING);

        Assert.assertNotNull(savedProducts);
        Assert.assertEquals(clothingProducts, savedProducts);
        verify(productRepository).findProductByProductType(CLOTHING);
    }

}
