package com.example.project.Controller;

import com.example.project.Model.Customer;
import com.example.project.Model.Product;
import com.example.project.Service.Customer.CustomerService;
import com.example.project.Service.Product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;



    @PostMapping()
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) throws Exception {
        return customerService.findCustomerById(id);
    }
    @GetMapping()
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public Customer UpdateCustomer(@PathVariable Long id,@RequestBody Customer customer) throws Exception{
        return customerService.updateCustomer(id,customer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);
    }
}
