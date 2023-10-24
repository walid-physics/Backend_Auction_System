package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
     List<Product> getProducts();

     List<Product> getSellerProducts() throws Exception;
     List<Product> getOpenProducts() throws Exception;
     Product getProductById(Integer id);

     void deleteProductById(Integer id) throws Exception;
     Product addProduct(Product product) throws Exception;
     Product updateProduct(Product product) throws Exception;
     List<Product> findProductByName(String name);

     Product publishProduct(Integer id) throws Exception;

     Product addBid(Integer productId, Double Amount) throws Exception;


}
