package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.BalanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceTransactionRepo extends JpaRepository<BalanceTransaction,Long> {
}
