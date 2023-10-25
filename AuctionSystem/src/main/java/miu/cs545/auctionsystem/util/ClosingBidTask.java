package miu.cs545.auctionsystem.util;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClosingBidTask {
    private final ProductService productService;

    @Scheduled(cron ="0/20 * * * * ?")
    public void run() {
        System.out.println(" closing bid task run Current time is :: " + LocalDateTime.now());
        try {
            productService.closeBids();
        } catch (Exception e) {
          e.printStackTrace();
        }

    }


}
