package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.ProductStatus;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.ProductService;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.repository.ProductRepo;
import miu.cs545.auctionsystem.util.ValidateUserFromToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepo productRepo;

    private final ValidateUserFromToken validateUserFromToken;


    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getSellerProducts() throws Exception {
        User user = validateUserFromToken.getUserFromAuthentication();
        return productRepo.findAllByProductOwnerOrderByBidDueDateDesc(user);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProductById(Integer id) throws Exception {
        User user = validateUserFromToken.getUserFromAuthentication();
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            if (!product.getStatus().equals(ProductStatus.PENDING))
                throw new Exception("Can't delete  product with not pending status");
            if (!product.getProductOwner().getId().equals(user.getId()))
                throw new Exception("Can't delete  another seller product ");
            productRepo.deleteById(id);
        } else
            throw new Exception("Product not found");


    }

    @Override
    public Product addProduct(Product product) throws Exception {
        User user = validateUserFromToken.getUserFromAuthentication();
        product.setProductOwner(user);
        if (product.getName() == null || product.getName().isEmpty())
            throw new Exception("Product name required");
        if (product.getDescription() == null || product.getDescription().isEmpty())
            throw new Exception("Product description required");

        System.out.println("product.getBidDueDate() " + product.getBidDueDate());
        if (product.getBidDueDate() == null || product.getBidDueDate().before(new Date()))
            throw new Exception("Due Date required and not in past");

        if (product.getPaymentDueDate() == null || product.getPaymentDueDate().before(new Date()))
            throw new Exception("Payment Due  Date required and not in past");
        if (product.getQuantity() == null || product.getQuantity() == 0)
            product.setQuantity(1.0);

        product.setWinnerCustomer(null);
        product.setDeposit(null);
        product.setSoldPrice(null);
        product.setCurrentPrice(null);
        product.setStatus(ProductStatus.PENDING);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws Exception {

        User user = validateUserFromToken.getUserFromAuthentication();

        Optional<Product> byId = productRepo.findById(product.getId());
        if (byId.isPresent()) {
            Product dbProduct = byId.get();
            if (!dbProduct.getStatus().equals(ProductStatus.PENDING))
                throw new Exception("Can't update  product with not pending status");
            if (!dbProduct.getProductOwner().getId().equals(user.getId()))
                throw new Exception("Can't update  another seller product ");
            return addProduct(product);
        } else
            throw new Exception("Product not found");


    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepo.findAllByNameContains(name);
    }

    @Override
    public Product publishProduct(Integer id) throws Exception {
        User user = validateUserFromToken.getUserFromAuthentication();

        Optional<Product> byId = productRepo.findById(id);
        if (byId.isPresent()) {
            Product dbProduct = byId.get();
            if (!dbProduct.getStatus().equals(ProductStatus.PENDING))
                throw new Exception("Can't publish  product with not pending status");
            if (!dbProduct.getProductOwner().getId().equals(user.getId()))
                throw new Exception("Can't publish  another seller product ");
            dbProduct.setStatus(ProductStatus.OPEN);
            return productRepo.save(dbProduct);

        } else
            throw new Exception("Product not found");

    }
}
