package miu.cs545.auctionsystem.contoller;


import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BalanceTransaction;

import miu.cs545.auctionsystem.service.BalanceTransactionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {
    private final BalanceTransactionService balanceTransactionService;

    @GetMapping
    public List<BalanceTransaction> getSellerProducts() throws Exception {
        return balanceTransactionService.findAllByUser();
    }
}
