package miu.cs545.auctionsystem.service.implService;




import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.Customer;
import miu.cs545.auctionsystem.repository.CustomerRepository;
import miu.cs545.auctionsystem.service.CustomerService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    @Override
    public Optional<Customer> findCustomerById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Customer not found with id " + id));
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setLicense(customer.getLicense());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }
}
