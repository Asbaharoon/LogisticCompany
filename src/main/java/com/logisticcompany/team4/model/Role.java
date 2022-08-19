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

import constant.RoleType;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	RoleType roleType;
	
	@OneToMany(mappedBy =  "role", cascade = CascadeType.ALL)
	List<User> users;

	public Role() {
	}

	public Role(String name, RoleType roleType) {
		this.name = name;
		this.roleType = roleType;}

	public Role(int id, String name, RoleType roleType, List<User> users) {
		this.id = id;
		this.name = name;
		this.roleType = roleType;
		this.users = users;
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

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
