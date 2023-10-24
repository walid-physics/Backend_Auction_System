package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    private final ProductService productService;


    @GetMapping("/seller")
    public List<Product> getSellerProducts() throws Exception {
        return productService.getSellerProducts();
    }

    @GetMapping("/customer")
    public List<Product> getOpenProducts() throws Exception {
        return productService.getOpenProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody Product product) throws Exception {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) throws Exception {
        return productService.updateProduct(product);
    }
    @DeleteMapping("/delete//{id}")
    public void deleteProduct(@PathVariable Integer id) throws Exception {
        productService.deleteProductById(id);
    }

    @PostMapping("/publish/{id}")
    public Product publishProduct(@PathVariable Integer id) throws Exception {
       return productService.publishProduct(id);
    }

    @GetMapping("/{name}")
    public List<Product> getProductsByName(@PathVariable String name){
         return productService.findProductByName(name);
    }



}
