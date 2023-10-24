package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.ProductStatus;
import miu.cs545.auctionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
List<Product> findAllByNameContains(String name);
<<<<<<< HEAD
List<Product> findAllByProductOwner_IdOrderByBidDueDateDesc(Integer User_id);
=======
List<Product> findAllByProductOwnerOrderByBidDueDateDesc(User user);
List<Product> findAllByStatusOrderByBidDueDate(ProductStatus status);
>>>>>>> f971462cab3ccc30b1899a6cdc7f4974993ea266

}
