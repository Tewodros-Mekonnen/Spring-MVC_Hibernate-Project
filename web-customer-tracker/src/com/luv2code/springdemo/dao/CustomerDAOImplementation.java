package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	// need to inject the session factory

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	/* @Transactional */// this annotation will take care of starting and committing session automagically!! but here we will
					// remove it because we will let the service layer handle this functionality. see CustomerServiceImpl.java
					// we are commenting it because, since i created CustomerServiceImpl.java, this functionality will be 
					// performed in there. see the architecture flow (controller <--> customerService <--> dao <-->db)
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the result
		return customers;

	}

}
