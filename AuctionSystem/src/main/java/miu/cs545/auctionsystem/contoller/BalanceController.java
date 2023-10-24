package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import miu.cs545.auctionsystem.util.ValidateUserFromToken;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
@CrossOrigin
public class BalanceController {

    private final BalanceTransactionService  balanceTransactionService;
private final ValidateUserFromToken validateUserFromToken;
    @PostMapping("/charge/{amount}")
    public BalanceTransaction Charge(@PathVariable Double amount) throws Exception {

      User user= validateUserFromToken.getUserFromAuthentication();
        return  balanceTransactionService.addToBalance(user,amount);

    }
}
