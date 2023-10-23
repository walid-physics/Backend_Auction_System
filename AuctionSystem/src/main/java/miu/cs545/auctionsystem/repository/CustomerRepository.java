package miu.cs545.auctionsystem.repository;


import miu.cs545.auctionsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
 Optional<Customer> getCustomerById(Long id);
}
