package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.User;

public interface BalanceTransactionService {

       BalanceTransaction addToBalance(User user, double amount);
}
