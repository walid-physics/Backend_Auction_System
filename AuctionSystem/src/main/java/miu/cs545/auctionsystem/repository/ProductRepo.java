package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.ProductStatus;
import miu.cs545.auctionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
List<Product> findAllByNameContains(String name);
List<Product> findAllByProductOwnerOrderByBidDueDateDesc(User user);
List<Product> findAllByStatusOrderByBidDueDate(ProductStatus status);
    List<Product> findAllByBidDueDateBeforeAndStatus( Date date,ProductStatus status);
    List<Product> findByPaymentDueDateBeforeAndStatus( Date date,ProductStatus status);

}
