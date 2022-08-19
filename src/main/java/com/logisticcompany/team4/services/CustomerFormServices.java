package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.CustomerForm;
import com.logisticcompany.team4.repository.CustomerFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerFormServices {

    @Autowired
    private CustomerFormRepository customerFormRepository;

    @Autowired
    private EmployeeServices employeeServices;

    public List<CustomerForm> findAll() {
        return customerFormRepository.findAll();
    }

    public List<CustomerForm> getUnansweredForms(@PathVariable("employeeId") int employeeId) {
        return employeeServices.findEmployeeById(employeeId).getOffice().getCompany().getCustomerForms().stream().filter(f -> !f.isAnswered()).collect(Collectors.toList());
    }

    public CustomerForm findById(@PathVariable("id") int id){
        return customerFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customerForm Id:" + id));
    }

    public void answerCustomerForm(@ModelAttribute CustomerForm form) {
        CustomerForm customerForm = findById(form.getId());
        customerForm.setAnswered(true);
        customerForm.setAnswer(form.getAnswer());
        customerFormRepository.save(customerForm);
    }

    public void addCustomerForm(@ModelAttribute CustomerForm customerForm) {
        customerFormRepository.save(customerForm);
    }

    public void deleteCustomerForm(@PathVariable("id") int id) {
        CustomerForm customerForm = customerFormRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer form Id:" + id));
        customerFormRepository.delete(customerForm);
    }
}
