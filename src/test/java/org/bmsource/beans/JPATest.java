package org.bmsource.beans;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.bmsource.model.Group;
import org.bmsource.model.User;

import junit.framework.TestCase;

public class JPATest extends TestCase {
	private static Logger logger = Logger.getLogger(JPATest.class.getName());

	private EntityManagerFactory emFactory;

	private EntityManager em;

	private String persistenceUnitName = "JEE6Demo-Persistence";

	public JPATest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			logger.info("BuildingEntityManager for unit tests");
			emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
			em = emFactory.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Exception during JPA EntityManager instanciation.");
		}
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		logger.info("Shuting Hibernate JPA layer.");
		if (em != null) {
			em.close();
		}
		if (emFactory != null) {
			emFactory.close();
		}
		logger.info("Stoppingemory database.");
	}

	public void testPersistence() {
		try {
			UserEJB userEJB = new UserEJB();
			userEJB.setEm(em);
			GroupEJB groupEJB = new GroupEJB();
			groupEJB.setEm(em);

			User u = new User();
			u.setFirstName("Marek");
			u.setLastName("Balla");
			u.setPassword("long-password");
			u.setLogin("marek@balla.com");

			Group g = new Group();
			g.setName("admins");

			em.getTransaction().begin();
			userEJB.createUser(u);
			groupEJB.createGroup(g);
			u.getGroups().add(g);
			g.getUsers().add(u);
			em.getTransaction().commit();

			u = userEJB.findUserById(u.getId());
			System.err.println(u);
			g = groupEJB.findGroupById(g.getId());
			System.err.println(g);

		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
			fail("Exception during testPersistence");
		}
	}
}
