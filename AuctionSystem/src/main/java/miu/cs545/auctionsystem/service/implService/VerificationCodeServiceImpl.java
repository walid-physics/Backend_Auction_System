package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.model.VerificationCode;
import miu.cs545.auctionsystem.repository.VerificationCodeRepo;
import miu.cs545.auctionsystem.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VerificationCodeServiceImpl implements VerificationCodeService {
    private VerificationCodeRepo verificationCodeRepo;

    @Autowired
    public VerificationCodeServiceImpl(VerificationCodeRepo verificationCodeRepo){
        this.verificationCodeRepo=verificationCodeRepo;
    }
    @Override
    public VerificationCode getVerificationCodeByUser(User user) {
        return verificationCodeRepo.findVerificationCodeByUser(user);
    }

    @Override
    public void deleteVerificationCodeByUser(User user) {
        VerificationCode verificationCode = getVerificationCodeByUser(user);
        verificationCodeRepo.delete(verificationCode);

    }

    @Override
    public VerificationCode saveVerificationCode(VerificationCode verificationCode) {
        return verificationCodeRepo.save(verificationCode);
    }

    @Override
    public VerificationCode updateVerificationCode(VerificationCode verificationCode) {
        VerificationCode verificationCode1 = verificationCodeRepo.findById(verificationCode.getId()).orElse(null);
        verificationCode1.setCode(verificationCode.getCode());
        verificationCode1.setRegistrationDate(verificationCode.getRegistrationDate());
        return verificationCode1;
    }
}
