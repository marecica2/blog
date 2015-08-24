package org.bmsource.beans;

import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.bmsource.model.Group;
import org.bmsource.model.User;

public class JPATest extends TestCase
{
    private static Logger logger = Logger.getLogger(JPATest.class.getName());

    private EntityManagerFactory emFactory;

    private EntityManager em;

    public JPATest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        try
        {
            logger.info("Startingemory database for unit tests");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DriverManager.getConnection("jdbc:derby:memory:StudentsDB;create=true").close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
            fail("Exception during database startup.");
        }
        try
        {
            logger.info("BuildingEntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("JEE6Demo-Persistence");
            em = emFactory.createEntityManager();
        } catch (Exception ex)
        {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        logger.info("Shuting Hibernate JPA layer.");
        if (em != null)
        {
            em.close();
        }
        if (emFactory != null)
        {
            emFactory.close();
        }
        logger.info("Stoppingemory database.");
        try
        {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex)
        {
            if (ex.getErrorCode() != 45000)
            {
                throw ex;
            }
            // Shutdown success
        }
        //VFMemoryStorageFactory.purgeDatabase(new File("unit-testing-jpa").getCanonicalPath());
    }

    public void testPersistence()
    {
        try
        {
            em.getTransaction().begin();
            User u = new User();
            u.setFirstName("eskatos");
            u.setLastName("YOP");
            em.persist(u);
            assertTrue(em.contains(u));
            Group g = new Group();
            em.persist(g);
            assertTrue(em.contains(g));
            em.remove(u);
            em.merge(g);
            assertFalse(em.contains(u));
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception during testPersistence");
        }
    }
}
