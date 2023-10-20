package miu.cs545.auctionsystem.repository;

import miu.cs545.auctionsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    public Payment findByUserId(Integer id);
}
