package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer") // this maps to the database
public class CustomerController {
	
	// need to inject the customer dao
	/*
	 * @Autowired private CustomerDAO customerDAO;
	 */
	
	// we don't need the above injection anymore, instead we will inject CustomerService as seen below
	// since the flow is as follows: 
	// controller <--> customerService <--> dao <--> db
	// the use of adding customer service is to be able to add different dao's, which means, if we need to connect to different
	// database
	
	
	// need to inject customerService here
	@Autowired
	private CustomerService customerService;
	
	
	/* @RequestMapping("/list") */
	@GetMapping("/list") // works same as @RequestMapping
	public String listCustomers(Model theModel) { 
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	// choose method name that is similar to the returning .jsp file
	// here we are returning list-customers.jsp file inside view folder. 

}
