package com.logisticcompany.team4.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "users_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String firstName;
	String lastName;
	
	String email;
	
	String username;
	String password;

	
	boolean isActive;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = true)
	Role role;
	
    @OneToOne(mappedBy = "user")
    private Employee employee;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;

	public User(){ };

	public User(int id, String firstName, String lastName, String email, String username, String password,
			boolean isActive, Role role, Employee employee, Customer customer) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.role = role;
		this.employee = employee;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
