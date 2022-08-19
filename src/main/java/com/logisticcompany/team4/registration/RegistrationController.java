package com.logisticcompany.team4.registration;

import com.logisticcompany.team4.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username
                           ,@RequestParam String password, @RequestParam String email, @RequestParam String address) {
        RegistrationRequest registrationRequest = new RegistrationRequest(
                firstName,
                lastName,
                username,
                email,
                password,
                address
        );
       registrationService.register(registrationRequest);
       return "/register_success";
    }




    @GetMapping(path = "/register")
    public String showRegistrationPage(Model model){
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "/register";}
}
