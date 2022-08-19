package com.logisticcompany.team4.security;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.EmployeeRepository;
import com.logisticcompany.team4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserPrincipalDetailService(UserRepository userRepository, EmployeeRepository employeeRepository,
                                      CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //returns an instance of UserDetailsInterface
    //finds the users in the DataBase
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = this.userRepository.findByUsername(s);
//        UserPrincipal userPrincipal = new UserPrincipal(user);
//
//        return userPrincipal;
//    }

    //finds the users in the DB by their email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(email);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }

    //if the email is active it is also present
    public void signUpUser (User user, Customer customer) {
    //Check if the email is already in use
        if (userRepository.
                findByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("Email already taken!");
        }

        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        customerRepository.save(customer);
    }

    public void signUpEmployee (User user, Employee employee) {
        //Check if the email is already in use
        if (userRepository.
                findByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("Email already taken!");
        }

        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        employeeRepository.save(employee);
    }
}
