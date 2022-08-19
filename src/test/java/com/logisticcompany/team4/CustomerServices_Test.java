package com.logisticcompany.team4;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.CompanyRepository;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.RoleRepository;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.services.CompanyServices;
import com.logisticcompany.team4.services.CustomerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CustomerServices_Test {

        @Autowired
        private CustomerRepository customerRepository;

        @Autowired
        private CustomerServices customerServices;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        private Customer FirstCustomer;
        private Customer SecondCustomer;

        @BeforeEach
        public void init(){
            Customer c1 = customerRepository.findById(1).get();
            Customer c2 = customerRepository.findById(2).get();
            FirstCustomer=c1;
            SecondCustomer=c2;
        }

        @Test
        public void getCustomersTest(){
            List<Customer> expectedListOfCustomers=new ArrayList<>();
            expectedListOfCustomers.add(FirstCustomer);
            expectedListOfCustomers.add(SecondCustomer);
            expectedListOfCustomers.add(new Customer(0, userRepository.findById(4).get(), "Place B"));
            expectedListOfCustomers.add(new Customer(0, userRepository.findById(1).get(), "Place C"));
            Assertions.assertEquals(expectedListOfCustomers.size()+1,customerServices.findAll().size());

        }

        @Test
        public void addCustomerTest(){
            Customer toAddCustomer=new Customer(0, userRepository.findById(1).get(), "Place F");
            customerServices.addCustomer(toAddCustomer);
            Assertions.assertEquals(customerServices.findAll().size(),5);
        }

        @Test
        public void getCustomerTest(){
            Customer customer=FirstCustomer;
            Customer customer1=customerServices.findCustomerById(FirstCustomer.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
            Assertions.assertEquals(customer.getAddress(),customer1.getAddress());
        }
        @Test
        public void UpdateCompanyTest() throws Exception{
            Customer toUpdateCustomer=new Customer(2, userRepository.findById(3).get(), "Place Q");
            customerServices.updateCustomer(toUpdateCustomer);
            Assertions.assertEquals("Carmen",customerServices.findCustomerById(SecondCustomer.getId()).getUser().getFirstName());
        }

        @Test
        public void deleteCompanyTest() throws Exception{
            Customer customer=customerRepository.save(new Customer(0,null,"Place F"));
            customerServices.deleteCustomer(customer.getId());
            Assertions.assertFalse(customerServices.findAll().contains(customer));
        }

}
