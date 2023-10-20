package miu.cs545.auctionsystem.dto.stripeDto;

import lombok.Data;

@Data
public class StripeTokenDto {
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String token;
    private String email;
    private boolean success;
}
