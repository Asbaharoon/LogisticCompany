package com.logisticcompany.team4.controller;

import java.util.List;

import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.services.ParcelServices;
import com.logisticcompany.team4.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.services.CompanyServices;

@Controller
public class CompanyController {

	@Autowired
	private CompanyServices companyServices;

	@Autowired
	private ParcelServices parcelServices;

	@Autowired
	private UserServices userService;

	@GetMapping(path = "/companies")
	public String showCompaniesPage(Model model) {
		List<Company> companies = companyServices.findAll();
		model.addAttribute("companies", companies);

		return "companies";
	}

	@GetMapping(path = "/companies/add")
	public String showAddCompanyPage(Model model) {
		model.addAttribute("company", new Company());
		return "companies-add";
	}

	@PostMapping(path = "/companies/add")
	public String addCompany(@ModelAttribute Company company) {
		companyServices.addCompany(company);

		return "redirect:/companies";
	}

	@GetMapping(path = "/companies/parcels")
	public String showParcels(Model model) {
		int companyId = userService.getCurrentUser().getEmployee().getOffice().getCompany().getId();
		List<Parcel> parcels = this.parcelServices.findAllByCompany(companyId);
		model.addAttribute("parcels", parcels);
		return "company-parcels";
	}

	@GetMapping("/companies/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Company company = companyServices.getCompanyById(id);
		model.addAttribute("company", company);
		return "companies-edit";
	}

	@PostMapping("/companies/edit/{id}")
	public String updateCompany(@ModelAttribute Company company) throws Exception {
		companyServices.updateCompany(company);
		return "redirect:/companies";
	}

	@GetMapping("/companies/delete/{id}")
	public String deleteCompany(@PathVariable("id") int id) {
		companyServices.deleteCompany(id);
		return "redirect:/companies";
	}
	
	@GetMapping("/companies/profit/{id}")
	@ResponseBody
	public String getCompanyProfit(@PathVariable("id") int id) {
		double revenues = companyServices.getRevenues(id);
		double expenses = companyServices.getExpenses(id);
		double profit = companyServices.getProfit(id);
		return String.format("revenues: %.02f <br/> expenses: %.02f </br> profit: %.02f", revenues, expenses, profit);
	}

}