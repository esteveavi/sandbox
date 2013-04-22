package com.example.sandbox.bo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.sandbox.model.Customer;

public class CustomerBO {


	public CustomerBO(){}

	@PersistenceContext
	private EntityManager entityManager;

	public Customer createCustomer(Customer customer){
		
		entityManager.persist(customer);
		return customer;
	}

}
