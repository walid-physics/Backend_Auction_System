package miu.cs545.auctionsystem.service;



import miu.cs545.auctionsystem.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
}
