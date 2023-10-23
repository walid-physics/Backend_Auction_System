package miu.cs545.auctionsystem.contoller;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;
import miu.cs545.auctionsystem.model.TransactionType;
import miu.cs545.auctionsystem.service.BalanceTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceTransactionService  balanceTransactionService;

    @PostMapping("/charge/{amount}")
    public BalanceTransaction Charge(@PathVariable Double amount)
    {
        return null;
    }
}
