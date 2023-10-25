package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceTransactionRepo extends JpaRepository<BalanceTransaction,Long> {
     List<BalanceTransaction> findAllByProductAndTransactionType(Product product,TransactionType type);
     BalanceTransaction findTopByProductAndTransactionTypeAndUser(Product product, TransactionType type, User user);
     List<BalanceTransaction> findAllByUser(User user);
}
