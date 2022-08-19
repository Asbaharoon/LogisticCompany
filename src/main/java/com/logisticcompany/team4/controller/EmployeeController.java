package com.logisticcompany.team4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.services.EmployeeServices;

import java.util.List;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServices employeeServices;
	
	@GetMapping(path = "/employees")
	public String showEmployeesPage(Model model) {
		List<Employee> employees = employeeServices.findAll(model);
		model.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping(path = "/employees/add")
	public String showAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees-add";
	}

	@PostMapping(path = "/employees/add")
	public String addEmployee(@ModelAttribute("employee") Employee employee) {
		employeeServices.addEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Employee employee = employeeServices.findEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees-edit";
	}

	@PostMapping("/employees/edit/{id}")
	public String updateEmployee(@ModelAttribute Employee employee) throws Exception {
		employeeServices.updateEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		employeeServices.deleteEmployee(id);
		return "redirect:/employees";
	}

}
