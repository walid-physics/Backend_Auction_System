package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;

import java.util.List;

public interface BalanceTransactionService {

       BalanceTransaction addToBalance(User user, double amount) throws Exception;
       BalanceTransaction addBidDeposit(User user, double amount, Product product) throws Exception;
       BalanceTransaction addTransaction(User user, double amount, TransactionType transactionType, Product product) throws Exception;

       void refund(Product product) throws Exception;
       Boolean pay(Product product) throws Exception;

       List<BalanceTransaction> findAllByUser() throws Exception;
}
