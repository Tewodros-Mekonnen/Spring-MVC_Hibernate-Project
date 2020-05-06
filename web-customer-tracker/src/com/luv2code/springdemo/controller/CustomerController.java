package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/myCustomers") // this maps to the database
public class CustomerController {

	// need to inject customerService here
	@Autowired
	private CustomerService customerService;

	/* @RequestMapping("/list") */
	@GetMapping("/list") // works same as @RequestMapping
	public String listCustomers(Model theModel) {

		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers); // the string "customers"(the attribute name) is used in the
															// list-customers.jsp as
															// a link

		return "list-customers";
	}
	// choose method name that is similar to the returning .jsp file
	// here we are returning list-customers.jsp file inside view folder.

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("saveCustomer") // "saveCustomer" is value of "action" inside customer-form.jsp, the same with @ModelAttribute("customer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/myCustomers/list";
		
	}

}
