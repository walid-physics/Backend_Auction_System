package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.repository.BalanceTransactionRepo;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import miu.cs545.auctionsystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BalanceTransactionServiceImpl  implements BalanceTransactionService {

    private  final BalanceTransactionRepo balanceTransactionRepo;
    private final UserService userService;

    @Override
    public BalanceTransaction addToBalance(User user, double amount) throws Exception {
        if(user==null)
            throw  new Exception(" User not Found");

        if(amount<=0)
            throw  new Exception(" Amount must be grater than zero.");
        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        if(user.getBalance()==null)
            user.setBalance(amount);
        else
            user.setBalance(user.getBalance().doubleValue() + amount);
        userService.updateUser(user);
        return balanceTransactionRepo.save(transaction);

    }

    @Override
    public BalanceTransaction addBidDeposit(User user, double amount, Product product) throws Exception {
        if(amount>0)
            amount=-1*amount;
        return addTransaction(user,amount,TransactionType.BIDDEPOSIT,product);
    }
    @Override
    public BalanceTransaction addTransaction(User user, double amount,TransactionType transactionType, Product product) throws Exception
    {
        if(user==null)
            throw  new Exception(" User not Found");
        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        System.out.println("product.getId() " +product.getId());
        transaction.setProduct(product);
        if(user.getBalance()==null)
            user.setBalance(amount);
        else
            user.setBalance(user.getBalance() + amount);

        System.out.println("user:" +user.getId());
        userService.updateUser(user);
        return balanceTransactionRepo.save(transaction);
    }
}
