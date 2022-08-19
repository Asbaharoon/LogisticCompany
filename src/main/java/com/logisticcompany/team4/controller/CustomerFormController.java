package com.logisticcompany.team4.controller;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.model.CustomerForm;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.services.CompanyServices;
import com.logisticcompany.team4.services.CustomerFormServices;
import com.logisticcompany.team4.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerFormController {

    @Autowired
    private CustomerFormServices customerFormServices;

    @Autowired
    private CompanyServices companyServices;

    @Autowired
    private UserServices userService;

    @GetMapping(path = "/customerForms")
    public String showAllFormsPage(Model model) {
        List<CustomerForm> customerForms = this.customerFormServices.findAll();
        model.addAttribute("customerForms", customerForms);

        return "customerForms";
    }


    @GetMapping(path = "/unansweredForms")
    public String showUnansweredFormsPage(Model model) {
        int employeeId = userService.getCurrentUser().getEmployee().getId();
        List<CustomerForm> unansweredForms = this.customerFormServices.getUnansweredForms(employeeId);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("unansweredForms", unansweredForms);

        return "customerForms-unanswered";
    }

    @GetMapping("/customerForms/answer/{id}")
    public String answerCustomerForm(Model model, @PathVariable("id") int id){
        CustomerForm customerForm = customerFormServices.findById(id);
        model.addAttribute("customerForm", customerForm);
        return "customerForms-answer";
    }

    @PostMapping("/customerForms/{id}")
    public String answerCustomerForm(@ModelAttribute CustomerForm customerForm) {
        customerFormServices.answerCustomerForm(customerForm);
        return "redirect:/unansweredForms";
    }

    @GetMapping(path = "/customerForms/add")
    public String showAddCustomerFormPage(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        model.addAttribute("companyList", companyServices.findAll());

        return "customerForms-add";
    }

    @PostMapping(path = "/customerForms/add")
    public String addCustomerForm(@ModelAttribute CustomerForm customerForm) {
        Company company = customerForm.getRelatedCompany();

        customerFormServices.addCustomerForm(customerForm);

        return "redirect:/";
    }

    @GetMapping("/customerForms/delete/{id}")
    public String deleteCustomerForm(@PathVariable("id") int id) {
        customerFormServices.deleteCustomerForm(id);
        return "redirect:/unansweredForms";
    }
}
