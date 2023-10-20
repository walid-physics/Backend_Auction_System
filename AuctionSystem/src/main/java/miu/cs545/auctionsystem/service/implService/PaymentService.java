package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.model.Payment;
import miu.cs545.auctionsystem.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentRepo paymentRepo;
    @Autowired
    public PaymentService(PaymentRepo paymentRepo){
        this.paymentRepo = paymentRepo;
    }
    public Payment findByUserId(Integer id){
        return paymentRepo.findByUserId(id);
    }

    public Payment save(Payment payment){
        return paymentRepo.save(payment);
    }


}
