package com.example.sandbox.model;

import com.example.sandbox.model.Customer;


import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Arquillian.class)
public class CustomerTest
{
   @Inject
   private Customer customer;

   @Deployment
	public static Archive<?> createDeployment() {
		return new DefaultDeployment().
				withPersistence().
				withImportedData().
				getArchive()
				//.addPackages(true, Cu.class.getPackage())
				;
	}

   @Test
   public void testIsDeployed()
   {
      Assert.assertNotNull(customer);
   }
}
