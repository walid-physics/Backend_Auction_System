package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.User;
import miu.cs545.auctionsystem.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepo extends JpaRepository<VerificationCode, Integer> {

    public VerificationCode findVerificationCodeByUser(User user);

}
