package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logisticcompany.team4.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CustomerServices {

	@Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll().stream().filter(c -> !c.isDeleted()).collect(Collectors.toList());
        return customers;
    }


    public void addCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
    }


    public Customer findCustomerById(@PathVariable("id") int id) {
        Customer customer = customerRepository.findById(id).filter(c -> !c.isDeleted()).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        return customer;
    }

    public void updateCustomer(@ModelAttribute Customer customer) throws Exception {
        Customer customerInDB = customerRepository.findById(customer.getId()).filter(c -> !c.isDeleted()).orElse(null);
        if (customerInDB != null) {
            customerInDB.setAddress(customer.getAddress());
            customerInDB.setUser(customer.getUser());
            customerRepository.save(customerInDB);
        } else {
            throw new Exception("Customer not found");
        }
    }

    public void deleteCustomer(@PathVariable("id") int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customer.setDeleted(true);
    }

}
