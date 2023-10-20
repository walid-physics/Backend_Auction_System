package miu.cs545.auctionsystem.contoller;

import com.stripe.exception.StripeException;
import miu.cs545.auctionsystem.dto.stripeDto.PaymentRequest;
import miu.cs545.auctionsystem.dto.stripeDto.StripeChargeDto;
import miu.cs545.auctionsystem.dto.stripeDto.StripeTokenDto;
import miu.cs545.auctionsystem.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/stripe")
public class StripeController {
    private StripeService stripeService;

    @Autowired
    public StripeController(StripeService stripeService){
        this.stripeService=stripeService;
    }

 /*   @PostMapping("/card/token")
    public StripeTokenDto getTokenForCard(@RequestBody StripeTokenDto stripeTokenDto){
        return stripeService.createCardToken(stripeTokenDto);
    }*/

    @PostMapping("/charge")
    public ResponseEntity<Object> charge(@RequestBody PaymentRequest paymentRequest)  {
        try{
            stripeService.charge(paymentRequest);
            return ResponseEntity.ok("Successful payment");
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));

        }
    }

    @PostMapping("/refund{id}")
    public ResponseEntity<Object> refund(@RequestParam Integer id)  {
        try{
            stripeService.refund(id);
            return ResponseEntity.ok("Successful refund");
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));

        }
    }

}
