package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional

public class CompanyServices {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> findAll() {
		List<Company> companies = companyRepository.findAll();
		return companies;
	}

	public void addCompany(@ModelAttribute Company company) {
		companyRepository.save(company);
	}

	public Company getCompanyById(@PathVariable("id") int id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
		return company;
	}

	public void updateCompany(@ModelAttribute Company company) throws Exception {
		Company companyInDB = companyRepository.findById(company.getId()).orElse(null);
		if (companyInDB != null) {
			companyInDB.setName(company.getName());
			companyInDB.setAddress(company.getAddress());
			companyRepository.save(companyInDB);
		} else {
			throw new Exception("Company not found");
		}
	}

	public void deleteCompany(@PathVariable("id") int id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
		companyRepository.delete(company);
	}

	public double getExpenses(int id) {
		Company company = companyRepository.findById(id).get();
		List<Employee> employees = company.getOffices().stream().flatMap(o->o.getEmployees().stream()).collect(Collectors.toList()); 
		List<Parcel> parcels = company.getOffices().stream().flatMap(o->o.getParcels().stream()).collect(Collectors.toList()); 

		double rents = company.getOffices().stream().map(o -> o.getRent()).reduce((a, b) -> a + b).orElse(0.0);
		double salaries = employees.stream().mapToDouble(e -> e.getSalary()).sum();
		double transportCosts = parcels.stream().mapToDouble(p->p.getTransportCost()).sum();
		
		return rents + salaries + transportCosts;
	}

	public double getRevenues(int id) {
		Company company = companyRepository.findById(id).get();
		double revenues = company.getOffices().stream().flatMap(o->o.getParcels().stream()).mapToDouble(p->p.getPrice()).sum();
		return revenues;
	}

	public double getProfit(int id) {
		return getRevenues(id) - getExpenses(id);
	}
}
