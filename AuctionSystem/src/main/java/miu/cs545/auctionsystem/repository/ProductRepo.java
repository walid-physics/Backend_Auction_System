package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
List<Product> findAllByNameContains(String name);

}
