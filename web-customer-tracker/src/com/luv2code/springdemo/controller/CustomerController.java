package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		// above the Model attribute name 'customer' is used inside customer-form.jsp, unlike 'customers' which is used inside list-customers.jsp 

		return "customer-form";
	}

	@PostMapping("saveCustomer") // "saveCustomer" is value of "action" inside customer-form.jsp, the same with
									// @ModelAttribute("customer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		customerService.saveCustomer(theCustomer);

		return "redirect:/myCustomers/list";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);

		// send it over to the appropriate form
		return "customer-form";

	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		// delete customer
		customerService.deleteCustomer(theId);

		return "redirect:/myCustomers/list";

	}

	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}

}
