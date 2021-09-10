package kokome.billing.product.api;

import kokome.billing.enumaration.ProductType;
import kokome.billing.product.entity.Product;
import kokome.billing.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oaitse on Sep, 10, 2021
 */

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    @PostMapping("/v1/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok().body(productService.saveProduct(product));
    }

    @GetMapping("/v1/all")
    public ResponseEntity<List<Product>> findAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/v1/byType/{productType}")
    public ResponseEntity<List<Product>> findUsersByRole(@PathVariable ProductType productType){
        return ResponseEntity.ok().body(productService.getProductByProductType(productType));
    }
}
