package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.*;
import miu.cs545.auctionsystem.repository.BalanceTransactionRepo;
import miu.cs545.auctionsystem.repository.ProductRepo;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import miu.cs545.auctionsystem.service.ProductService;
import miu.cs545.auctionsystem.service.UserService;
import miu.cs545.auctionsystem.util.ValidateUserFromToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceTransactionServiceImpl implements BalanceTransactionService {

    private final BalanceTransactionRepo balanceTransactionRepo;
    private final UserService userService;
    private final ValidateUserFromToken validateUserFromToken;


    @Override
    public BalanceTransaction addToBalance(User user, double amount) throws Exception {
        if (user == null)
            throw new Exception(" User not Found");

        if (amount <= 0)
            throw new Exception(" Amount must be grater than zero.");
        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        if (user.getBalance() == null)
            user.setBalance(amount);
        else
            user.setBalance(user.getBalance().doubleValue() + amount);
        userService.updateUser(user);
        return balanceTransactionRepo.save(transaction);

    }

    @Override
    public BalanceTransaction addBidDeposit(User user, double amount, Product product) throws Exception {
        if (amount > 0)
            amount = -1 * amount;
        return addTransaction(user, amount, TransactionType.BIDDEPOSIT, product);
    }

    @Override
    public BalanceTransaction addTransaction(User user, double amount, TransactionType transactionType, Product product) throws Exception {
        if (user == null)
            throw new Exception(" User not Found");
        BalanceTransaction transaction = new BalanceTransaction();
        transaction.setUser(user);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        System.out.println("product.getId() " + product.getId());
        transaction.setProduct(product);
        if (user.getBalance() == null)
            user.setBalance(amount);
        else
            user.setBalance(user.getBalance() + amount);

        System.out.println("user:" + user.getId());
        userService.updateUser(user);
        return balanceTransactionRepo.save(transaction);
    }

    @Override
    public void refund(Product product) throws Exception {
        List<BalanceTransaction> transActions = balanceTransactionRepo.findAllByProductAndTransactionType(product, TransactionType.BIDDEPOSIT);
        for (BalanceTransaction tr : transActions) {
            if (!tr.getUser().getId().equals(product.getWinnerCustomer().getId()))
                addTransaction(tr.getUser(), -1 * tr.getAmount(), TransactionType.REFUND, product);
           // product.setStatus(ProductStatus.CLOSED);
           // ProductRepo.save(product);
            /*else
            {
                Double requiredAmount= product.getCurrentPrice() + tr.getAmount();
                if(product.getWinnerCustomer().getBalance() >=requiredAmount)
                {
                    addTransaction(product.getWinnerCustomer(),-1*requiredAmount,TransactionType.BIDPAYMENT,product);
                    addTransaction(product.getProductOwner(), requiredAmount,TransactionType.PAYMENT,product);
                    product.setStatus(ProductStatus.CLOSED);
                }else
                {
                    product.setStatus(ProductStatus.PENDING);
                }
                product.setStatus(ProductStatus.CLOSED);
                ProductRepo.save(product);


            }*/


        }

    }

    @Override
    public Boolean pay(Product product) throws Exception {
        BalanceTransaction bidDeposit = balanceTransactionRepo.findTopByProductAndTransactionTypeAndUser(product, TransactionType.BIDDEPOSIT, product.getWinnerCustomer());
        if (bidDeposit != null) {
            Double requiredAmount = product.getCurrentPrice() + bidDeposit.getAmount();
            if (product.getWinnerCustomer().getBalance() >= requiredAmount) {
                addTransaction(product.getWinnerCustomer(),   -1 * requiredAmount, TransactionType.BIDPAYMENT, product);
                addTransaction(product.getProductOwner(), product.getCurrentPrice() , TransactionType.PAYMENT, product);
              //  product.setStatus(ProductStatus.PAID);
                return true;
            } else {
               // product.setStatus(ProductStatus.PENDING);
                return false;
            }



        }
        return false;
    }

    @Override
    public List<BalanceTransaction> findAllByUser() throws Exception {
        User user = validateUserFromToken.getUserFromAuthentication();
        return balanceTransactionRepo.findAllByUser(user);

    }
}
