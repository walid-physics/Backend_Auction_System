package miu.cs545.auctionsystem.service.implService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.model.Token;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.TokenCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import miu.cs545.auctionsystem.dto.stripeDto.PaymentRequest;
import miu.cs545.auctionsystem.dto.stripeDto.StripeChargeDto;
import miu.cs545.auctionsystem.dto.stripeDto.StripeTokenDto;
import miu.cs545.auctionsystem.model.Payment;
import miu.cs545.auctionsystem.service.StripeService;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeServiceImpl implements StripeService {
    private PaymentService paymentService;
    @Autowired
    public StripeServiceImpl(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey=stripeApiKey;
    }

    public void refund(Integer id){
        try{
            String chargeId = paymentService.findByUserId(id).getChargeId();
            Refund.create(new RefundCreateParams.Builder().setCharge(chargeId).build());

        } catch (StripeException e) {
            log.error("StripeService(refund)", e);
            throw new RuntimeException(e);
        }

    }

    public Charge charge(PaymentRequest paymentRequest) {
        try{
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(paymentRequest.getAmount() * 100));
        params.put("currency", "USD");
        params.put("source", paymentRequest.getToken());

        Charge charge = Charge.create(params);
        Payment payment = new Payment();
        payment.setChargeId(charge.getId());
        payment.setUserId(paymentRequest.getUserId());
        paymentService.save(payment);
        return charge;

        }
        catch (StripeException e){
            log.error("StripeService(charge)", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public StripeChargeDto chargeCreditCard(StripeChargeDto stripeChargeDto) {
        try{
            stripeChargeDto.setSuccess(false);
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int)(stripeChargeDto.getAmount()*100));
            params.put("currency","USD");
            params.put("description", "Payment for Id" + stripeChargeDto.getAdditionalInfo().getOrDefault("ID_TAG",""));
            params.put("source", stripeChargeDto.getStripeToken());

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", stripeChargeDto.getChargeId());
            metaData.putAll(stripeChargeDto.getAdditionalInfo());
            params.put("metadata", metaData);
            Charge charge = Charge.create(metaData);

            stripeChargeDto.setMessage(charge.getOutcome().getSellerMessage());

            if(charge.getPaid()){
                stripeChargeDto.setChargeId(charge.getId());
                stripeChargeDto.setSuccess(true);
            }
            return stripeChargeDto;

        } catch (StripeException e) {
            log.error("StripeService(charge)", e);
            throw new RuntimeException(e);
        }
    }
}
