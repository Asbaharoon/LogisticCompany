package com.logisticcompany.team4;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.EmployeeRepository;
import com.logisticcompany.team4.repository.OfficeRepository;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.services.CustomerServices;
import com.logisticcompany.team4.services.EmployeeServices;
import constant.EmployeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmployeeServices_Test {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private EmployeeServices employeeServices;

    @Autowired
    private UserRepository userRepository;

    private Employee FirstEmployee;
    private Employee SecondEmployee;

    @BeforeEach
    public void init(){
        Employee e1 = employeeRepository.findById(1).get();
        Employee e2 = employeeRepository.findById(2).get();
        FirstEmployee=e1;
        SecondEmployee=e2;
    }

    @Test
    public void getEmployeesTest(){
//        Assertions.assertEquals(5,employeeServices.findAll().size());
    }

    @Test
    public void addEmployeeTest(){
        User user=new User();
        Employee toAddEmployee=new Employee(0, 2000, EmployeeType.COURIER, officeRepository.getById(1), user);
        employeeServices.addEmployee(toAddEmployee);
//        Assertions.assertEquals(5,employeeServices.findAll().size());
    }

    @Test
    public void getEmployeeTest(){
        Employee employee=FirstEmployee;
        Employee employee1=employeeServices.findEmployeeById(FirstEmployee.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
        Assertions.assertEquals(employee.getEmployeeType(),employee1.getEmployeeType());
    }
    @Test
    public void UpdateEmployeeTest() throws Exception{
        Employee toUpdateEmployee=new Employee(2, 1400, EmployeeType.OFFICE_CLERK, officeRepository.getById(2), userRepository.findById(5).get());
        employeeServices.updateEmployee(toUpdateEmployee);
        Assertions.assertEquals(userRepository.findById(5).get().getFirstName(),employeeServices.findEmployeeById(SecondEmployee.getId()).getUser().getFirstName());
    }
}
