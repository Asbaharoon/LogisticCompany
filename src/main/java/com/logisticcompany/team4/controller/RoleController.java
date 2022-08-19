package com.logisticcompany.team4.controller;

import com.logisticcompany.team4.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.logisticcompany.team4.model.Role;

import java.util.List;


@Controller
public class RoleController {

	@Autowired
	private RoleServices roleServices;

	@GetMapping(path = "/roles")
	public String showRolesPage(Model model) {
		List<Role> roles = roleServices.findAll();
		model.addAttribute("roles", roles);
		return "roles";
	}

	@GetMapping(path = "/roles/add")
	public String showAddRolePage(Model model) {
		model.addAttribute("role", new Role());
		return "roles-add";
	}

	@PostMapping(path = "/roles/add")
	public String addRole(@ModelAttribute Role role) {
		roleServices.addRole(role);
		return "redirect:/roles";
	}

	@GetMapping("/roles/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Role role = roleServices.findRoleById(id);
		model.addAttribute("role", role);
		return "roles-edit";
	}

	@PostMapping("/roles/edit/{id}")
	public String updateRole(@ModelAttribute Role role) throws Exception {
		roleServices.updateRole(role);
		return "redirect:/roles";
	}

	@GetMapping("/roles/delete/{id}")
	public String deleteRole(@PathVariable("id") int id) {
		roleServices.deleteRole(id);
		return "redirect:/roles";
	}

}
