package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Integer id);

    public void deleteProductById(Integer id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);

}
