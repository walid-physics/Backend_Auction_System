package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.User;
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

     List<Product> findAllByNameContains(String name);


     Product addBid(Integer productId, Double Amount) throws Exception;
     void closeBids() throws Exception;

     void PayClosedProduct() throws Exception;



}
