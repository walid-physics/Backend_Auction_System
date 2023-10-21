package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.Product;
import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.model.VerificationCode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VerificationCodeService {

    //public List<Product> getProducts();
    public VerificationCode getVerificationCodeByUser(User user);

    public void deleteVerificationCodeByUser(User user);
    public VerificationCode saveVerificationCode(VerificationCode verificationCode);
    public VerificationCode updateVerificationCode(VerificationCode verificationCode);
}
