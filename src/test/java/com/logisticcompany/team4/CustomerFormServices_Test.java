package com.logisticcompany.team4;

import com.logisticcompany.team4.model.CustomerForm;
import com.logisticcompany.team4.repository.CustomerFormRepository;
import com.logisticcompany.team4.services.CustomerFormServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CustomerFormServices_Test {

    @Autowired
    private CustomerFormRepository customerFormRepository;

    @Autowired
    private CustomerFormServices customerFormServices;

    private CustomerForm FirstForm;
    private CustomerForm SecondForm;

    @BeforeEach
    public void init(){
        CustomerForm cf1 = customerFormRepository.findById(1).get();
        CustomerForm cf2 = customerFormRepository.findById(2).get();
        FirstForm=cf1;
        SecondForm=cf2;
    }

    @Test
    public void getCustomerFormsTest(){
        List<CustomerForm> expectedListOfCustomerForms=new ArrayList<>();
        expectedListOfCustomerForms.add(FirstForm);
        expectedListOfCustomerForms.add(SecondForm);
        expectedListOfCustomerForms.add(customerFormRepository.getById(3));
        expectedListOfCustomerForms.add(customerFormRepository.getById(4));
        expectedListOfCustomerForms.add(customerFormRepository.getById(5));
        Assertions.assertEquals(expectedListOfCustomerForms.size()+1,customerFormServices.findAll().size());

    }

    @Test
    public void addCustomerTest(){
        CustomerForm toAddCustomerForm=new CustomerForm(0, "Nick", "Nickson", "n.n@gmail.com", "0888888888", "nqkuv content", null);
        customerFormServices.addCustomerForm(toAddCustomerForm);
        Assertions.assertEquals(6,customerFormServices.findAll().size());
    }

    @Test
    public void getCustomerTest(){
        CustomerForm customerForm=FirstForm;
        CustomerForm customerForm1=customerFormServices.findById(FirstForm.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
        Assertions.assertEquals(customerForm.getContent(),customerForm1.getContent());
    }
    @Test
    public void AnswerCustomerFormTest() throws Exception{
        SecondForm.setAnswer("Okay");
        customerFormServices.answerCustomerForm(SecondForm);
        Assertions.assertFalse(customerFormServices.findById(SecondForm.getId()).getAnswer().isEmpty());
    }

        @Test
        public void deleteCompanyTest() throws Exception{
            CustomerForm customerForm=customerFormRepository.save(new CustomerForm(0, "Nick", "Nickson", "n.n@gmail.com", "0888888888", "nqkuv content", null));
            customerFormServices.deleteCustomerForm(customerForm.getId());
            Assertions.assertFalse(customerFormServices.findAll().contains(customerForm));
        }

}
