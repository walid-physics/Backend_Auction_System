package miu.cs545.auctionsystem.dto.stripeDto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StripeChargeDto {
    private String stripeToken;
    private String email;
    private Double amount;
    private boolean success;
    private String chargeId;
    private String message;
    private Map<String, Object> additionalInfo = new HashMap<>();
}
