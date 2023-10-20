package miu.cs545.auctionsystem.dto.stripeDto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String token;
    private double amount;
    private Integer userId;
}
