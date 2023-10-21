package com.example.project.Service.Customer;

import com.example.project.Model.Customer;
import com.example.project.Model.Product;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
}
