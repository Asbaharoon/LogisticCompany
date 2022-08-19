package com.logisticcompany.team4.controller;


import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.registration.RegistrationRequest;
import com.logisticcompany.team4.registration.RegistrationService;
import com.logisticcompany.team4.services.OfficeServices;
import constant.EmployeeType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class AdminController {

    private RegistrationService registrationService;
    private OfficeServices officeServices;


    @PostMapping(path = "/register_employee")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username
        , @RequestParam String password, @RequestParam String email, @RequestParam double salary,
                           @RequestParam String employeeType, @RequestParam Office office) {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                firstName,
                lastName,
                username,
                email,
                password,
                salary,
                employeeType,
                office
        );
        registrationService.registerEmployee(registrationRequest);
        return "/reports";
    }


    @GetMapping(path = "/register_employee")
    public String showRegistrationPage(Model model){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        List<Office> offices = officeServices.findAll();
        model.addAttribute("registrationRequest", registrationRequest);
        model.addAttribute("offices", offices);
        return "/register_employee";}
}
