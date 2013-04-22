package com.example.sandbox.model;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.sandbox.bo.CustomerBO;

@RunWith(Arquillian.class)
public class CustomerTransactionTest
{
	@Inject
	private Customer customer;

	@Deployment
	public static Archive<?> createDeployment() {
		return new DefaultDeployment().
				withPersistence().
				withImportedData().
				getArchive()
				.addPackages(true, Customer.class.getPackage())
				;
	}



	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private CustomerBO customerBO;

	@Test
	public void testIsDeployed()
	{
		Assert.assertNotNull(customer);
		Assert.assertNotNull(customerBO);
	}

	@Test
	@Transactional
	public void testCreate()
	{
		Customer customer =  new Customer();
		customer.setFirstName("Test 1");
		customer.setLastName("Testa ");
		customer.setCreationDate(new Date());
		entityManager.persist(customer);
		entityManager.flush();
		Assert.assertNotNull(customer.getId());
	}

	@Test
	@Transactional
	public void testCustomerBO()
	{
		
		Customer customer =  new Customer();
		customer.setFirstName("Test 1");
		customer.setLastName("Testa ");
		customer.setCreationDate(new Date());
		customerBO.createCustomer(customer);
		Assert.assertNotNull(customer.getId());
	}
	 



}
