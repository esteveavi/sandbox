package com.example.sandbox.services.persistence;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * <p>FesPackEntityManagerProducer class.</p>
 *
 * @author esteveavi
 * @version $Id: $
 */
public class FesPackEntityManagerProducer {


	
	@PersistenceContext(unitName="fespackPersistenceUnit",type=PersistenceContextType.EXTENDED)
	//@PersistenceContext(unitName="fespackPersistenceUnit")
	@Default
	private EntityManager entityManager;


	@Produces
	@FesPackEntityManager
	//@TransactionScoped
	public EntityManager createEntityManager()
	{
		System.out.println("Creating EntityManager for fespackPersistenceUnit");
		return this.entityManager;
	}

	public void dispose(@Disposes @FesPackEntityManager EntityManager entityManager)
	{
		System.out.println("Closing EntityManager for fespackPersistenceUnit");
		/*
		if(entityManager.isOpen())
		{
			entityManager.close();
		}
		*/
	}
}
