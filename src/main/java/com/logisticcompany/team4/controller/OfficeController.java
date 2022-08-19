package com.logisticcompany.team4.controller;

import com.logisticcompany.team4.services.OfficeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.logisticcompany.team4.model.Office;

import java.util.List;


@Controller
public class OfficeController {
	
	@Autowired
	private OfficeServices officeServices;
	
	@GetMapping(path = "/offices")
	public String showOfficesPage(Model model) {
		List<Office> offices = officeServices.findAll();
		model.addAttribute("offices", offices);
		return "offices";
	}

	@GetMapping(path = "/offices/add")
	public String showAddOfficePage(Model model) {
		model.addAttribute("office", new Office());
		return "offices-add";
	}

	@PostMapping(path = "/offices/add")
	public String addOffice(@ModelAttribute Office office) {
		 officeServices.addOffice(office);
		 return "redirect:/offices";
	}

	@GetMapping("/offices/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Office office = officeServices.findOfficeById(id);
		model.addAttribute("office", office);
		return "offices-edit";
	}

	@PostMapping("/offices/edit/{id}")
	public String updateOffice(@ModelAttribute Office office) throws Exception {
		officeServices.updateOffice(office);
		return "redirect:/offices";
	}

	@GetMapping("/offices/delete/{id}")
	public String deleteOffice(@PathVariable("id") int id) {
		officeServices.deleteOffice(id);
		return "redirect:/offices";
	}

}
