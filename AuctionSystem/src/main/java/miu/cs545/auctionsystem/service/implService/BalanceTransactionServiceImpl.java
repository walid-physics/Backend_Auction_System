package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.repository.BalanceTransactionRepo;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BalanceTransactionServiceImpl  implements BalanceTransactionService {

    private  final BalanceTransactionRepo balanceTransactionRepo;

    @Override
    @Transactional
    public BalanceTransaction addToBalance(User user, double amount) {
        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        balanceTransactionRepo.save(transaction);
        return transaction;
    }
}
