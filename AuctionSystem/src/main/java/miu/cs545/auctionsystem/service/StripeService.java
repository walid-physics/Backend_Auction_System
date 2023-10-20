package miu.cs545.auctionsystem.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import miu.cs545.auctionsystem.dto.stripeDto.PaymentRequest;
import miu.cs545.auctionsystem.dto.stripeDto.StripeChargeDto;
import miu.cs545.auctionsystem.dto.stripeDto.StripeTokenDto;

public interface StripeService {
    //public StripeTokenDto createCardToken(StripeTokenDto model);
    public StripeChargeDto chargeCreditCard(StripeChargeDto stripeChargeDto) throws StripeException;
    public Charge charge(PaymentRequest paymentRequest) ;
    public void refund(Integer id);

}
