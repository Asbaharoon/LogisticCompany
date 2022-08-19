package com.logisticcompany.team4.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {
	
	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String address;

	@OneToMany(mappedBy =  "company", cascade = CascadeType.ALL)
	List<Office> offices;

	@OneToMany(mappedBy =  "relatedCompany", cascade = CascadeType.ALL)
	List<CustomerForm> customerForms;

	public Company() {
	}

	public Company(int id, String name, String address, List<Office> offices, List<CustomerForm> customerForms) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.offices = offices;
		this.customerForms = customerForms;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public List<CustomerForm> getCustomerForms() {
		return customerForms;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

}
