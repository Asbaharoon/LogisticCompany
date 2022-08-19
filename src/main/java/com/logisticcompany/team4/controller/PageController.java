package com.logisticcompany.team4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

	@GetMapping(path ="/")
	public String showCompaniesPage() {
		return "index";
	}


	@GetMapping(path = "/login")
	public String showLoginPage(){return "login";}

	@GetMapping(path = "/operations")
	public String showOperationsPage(){return "operations";}

	@GetMapping(path = "/reports")
	public String showReportsPage(){return "reports";}

}