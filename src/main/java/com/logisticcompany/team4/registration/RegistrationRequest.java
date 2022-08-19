package com.logisticcompany.team4.registration;

import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.model.Role;
import constant.EmployeeType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    private double salary;
    private String employeeType;
    private Office office;

    public RegistrationRequest() {}

    public RegistrationRequest(String firstName, String lastName, String username, String email, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public RegistrationRequest(String firstName, String lastName, String username, String email,
                               String password, double salary, String employeeType, Office office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.employeeType = employeeType;
        this.office = office;
    }

}
