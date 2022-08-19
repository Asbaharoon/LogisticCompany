package com.logisticcompany.team4.controller;

import com.logisticcompany.team4.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.logisticcompany.team4.model.User;

import java.util.List;


@Controller
public class UserController {

	@Autowired
	private UserServices userServices;

	@GetMapping(path = "/users")
	public String showUsersPage(Model model) {
		List<User> users = userServices.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping(path = "/users/add")
	public String showAddUserPage(Model model) {
		model.addAttribute("user", new User());
		return "users-add";
	}

	@PostMapping(path = "/users/add")
	public String addUser(@ModelAttribute User user) {
		userServices.addUser(user);
		return "redirect:/users";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		User user = userServices.findUserById(id);
		model.addAttribute("user", user);
		return "users-edit";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@ModelAttribute User user) throws Exception {
		userServices.updateUser(user);
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userServices.deleteUser(id);
		return "redirect:/users";
	}

}
