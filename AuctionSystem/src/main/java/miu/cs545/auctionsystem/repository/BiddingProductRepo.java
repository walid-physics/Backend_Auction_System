package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.BiddingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingProductRepo extends JpaRepository<BiddingProduct, Integer> {
    public List<BiddingProduct> findByStatusTrue();
}
