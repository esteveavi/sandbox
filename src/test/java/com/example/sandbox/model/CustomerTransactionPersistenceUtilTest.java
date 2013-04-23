package com.example.sandbox.model;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.PersistenceTest;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.sandbox.bo.CustomerPersistenceUtilBO;
/* mvn test -Dtest=CustomerTransactionPersistenceUtilTest -Parq-jboss_as_managed_7.x */
@RunWith(Arquillian.class)
//@PersistenceTest
public class CustomerTransactionPersistenceUtilTest
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




	@Inject
	private CustomerPersistenceUtilBO customerBO;

	@Test
	@Transactional
	public void testIsDeployed()
	{
		Assert.assertNotNull(customer);
		Assert.assertNotNull(customerBO);
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
	 

	@Test
	public void testFindCustomerBO()
	{
		Customer customer = customerBO.getCustomer(new Long(1));
		Assert.assertNotNull(customer);
	}
	 


}
