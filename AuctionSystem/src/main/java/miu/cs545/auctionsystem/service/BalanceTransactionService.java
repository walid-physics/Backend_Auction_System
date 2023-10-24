package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;

public interface BalanceTransactionService {

       BalanceTransaction addToBalance(User user, double amount) throws Exception;
       BalanceTransaction addBidDeposit(User user, double amount, Product product) throws Exception;
       BalanceTransaction addTransaction(User user, double amount, TransactionType transactionType, Product product) throws Exception;
}
