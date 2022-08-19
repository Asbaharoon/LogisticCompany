package com.logisticcompany.team4.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {

	@Id
	@Column(name = "office_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String address;
	
	//additional feature
	double rent;
	
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	Company company;
	
	@OneToMany(mappedBy =  "office", cascade = CascadeType.ALL)
	List<Employee> employees;

	@OneToMany(mappedBy =  "sentFromOffice", cascade = CascadeType.ALL)
	List<Parcel> parcels;
	
	public Office() {
	}

	public Office(int id, String address, double rent, Company company, List<Employee> employees, List<Parcel> parcels ) {
		this.id = id;
		this.address = address;
		this.rent = rent;
		this.company = company;
		this.employees = employees;
		this.parcels = parcels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}
	
}
