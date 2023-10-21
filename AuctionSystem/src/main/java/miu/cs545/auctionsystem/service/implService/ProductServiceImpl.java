package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.service.ProductService;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        //do it in a proper way

        return productRepo.save(product);
    }
    @Override
    public List<Product> findProductByName(String name) {
        return productRepo.findAllByNameContains(name);
    }
  
}
