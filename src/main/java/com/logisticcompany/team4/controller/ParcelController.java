package com.logisticcompany.team4.controller;

import com.logisticcompany.team4.services.ParcelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.logisticcompany.team4.model.Parcel;

import java.util.List;


@Controller
public class ParcelController {

	@Autowired
	private ParcelServices parcelServices;

	@GetMapping(path = "/parcels")
	public String showParcelsPage(Model model) {
		List<Parcel> parcels = parcelServices.findAll();
		model.addAttribute("parcels", parcels);
		return "parcels";
	}

	@GetMapping(path = "/parcels/add")
	public String showAddParcelPage(Model model) {
		model.addAttribute("parcel", new Parcel());
		return "parcels-add";
	}

	@PostMapping(path = "/parcels/add")
	public String addParcel(@ModelAttribute Parcel parcel) {
		parcelServices.addParcel(parcel);
		return "redirect:/companies/parcels";
	}

	@GetMapping("/parcels/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Parcel parcel = parcelServices.findParcelById(id);
		model.addAttribute("parcel", parcel);
		return "parcels-edit";
	}

	@PostMapping("/parcels/edit/{id}")
	public String updateParcel(@ModelAttribute Parcel parcel) throws Exception {
		parcelServices.updateParcel(parcel);
		return "redirect:/companies/parcels";
	}

	@GetMapping("/parcels/delete/{id}")
	public String deleteParcel(@PathVariable("id") int id) {
		parcelServices.deleteParcel(id);
		return "redirect:/companies/parcels";
	}

}
