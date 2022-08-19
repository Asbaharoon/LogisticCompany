package com.logisticcompany.team4.registration;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.EmployeeRepository;
import com.logisticcompany.team4.repository.RoleRepository;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.security.UserPrincipalDetailService;
import com.logisticcompany.team4.services.UserServices;
import constant.EmployeeType;
import constant.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailValidator emailValidator;
    private UserServices userServices;
    private UserPrincipalDetailService userPrincipalDetailService;


    //This creates a user with RoleType CUSTOMER
    public void register(RegistrationRequest registrationRequest) {
        boolean isValidEmail = emailValidator.test(registrationRequest.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }

        User user = new User(
                0,
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                true,
                this.roleRepository.findById(2).get(),
                null,
                null
        );
        Customer customer =  new Customer(
                        0,
                        user,
                        registrationRequest.getAddress()
        );

        userPrincipalDetailService.signUpUser(user, customer);
    }

    public void registerEmployee(RegistrationRequest registrationRequest) {
        boolean isValidEmail = emailValidator.test(registrationRequest.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        User user = new User(
                0,
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                true,
                this.roleRepository.findById(1).get(),
                null,
                null
        );
        Employee employee = new Employee(
                0,
                registrationRequest.getSalary(),
                EmployeeType.valueOf(registrationRequest.getEmployeeType()),
                registrationRequest.getOffice(),
                user
        );

        userPrincipalDetailService.signUpEmployee(user, employee);
    }
}
