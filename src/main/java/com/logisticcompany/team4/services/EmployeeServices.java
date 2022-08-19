package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public void addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
    }


    public Employee findEmployeeById(@PathVariable("id") int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        return employee;
    }


    public void updateEmployee(@ModelAttribute Employee employee) throws Exception {
        Employee employeeInDB = employeeRepository.findById(employee.getId()).orElse(null);
        if (employeeInDB != null) {
            employeeInDB.setUser(employee.getUser());
            employeeRepository.save(employeeInDB);
        } else {
            throw new Exception("Employee not found");
        }
    }


    public void deleteEmployee(@PathVariable("id") int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
    }
}
