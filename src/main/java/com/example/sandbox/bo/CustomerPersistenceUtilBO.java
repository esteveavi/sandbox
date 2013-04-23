package com.example.sandbox.bo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.sandbox.model.Customer;
import com.example.sandbox.services.persistence.PersistenceUtil;

public class CustomerPersistenceUtilBO {


	public CustomerPersistenceUtilBO(){}

	@Inject
	private PersistenceUtil persistenceUtil;

	public Customer createCustomer(Customer customer){
		persistenceUtil.persist(customer);
		return customer;
	}
	
	public Customer getCustomer(Long id){
		return persistenceUtil.findById(Customer.class, id);
	}

}
