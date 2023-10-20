package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.BiddingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiddingSystemRepo extends JpaRepository<BiddingSystem, Integer> {


}
