package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.repository.BalanceTransactionRepo;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BalanceTransactionServiceImpl  implements BalanceTransactionService {

    private  final BalanceTransactionRepo balanceTransactionRepo;
    private final UserService userService;

    @Override
    @Transactional
    public BalanceTransaction addToBalance(User user, double amount) throws Exception {
        if(user==null)
            throw  new Exception(" User not Found");

        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        if(user.getBalance()==null)
            user.setBalance(amount);
        else
            user.setBalance(user.getBalance() + amount);
        userService.updateUser(user);
        return balanceTransactionRepo.save(transaction);


    }
}
