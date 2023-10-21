package miu.cs545.auctionsystem.contoller;

import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping()
    public List<Product> getALlProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

 @GetMapping("/{name}")
    public List<Product> getProductsByName(@PathVariable String name){
        return productService.findProductByName(name);
    }

}
