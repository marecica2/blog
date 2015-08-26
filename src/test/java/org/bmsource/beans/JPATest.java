package org.bmsource.beans;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.bmsource.model.Group;
import org.bmsource.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPATest
{
    private static Logger logger = Logger.getLogger(JPATest.class.getName());
    private static EntityManagerFactory emFactory;
    private EntityManager em;
    private final static String PERSISTENCE_UNIT_NAME = "JEE6Demo-Persistence";
    private final UserEJB userEJB = new UserEJB();
    private final GroupEJB groupEJB = new GroupEJB();

    @BeforeClass
    public static void init()
    {
        logger.info("init begin");
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        logger.info("init end");
        logger.info("");
    }

    @AfterClass
    public static void after()
    {
        if (emFactory != null)
            emFactory.close();
    }

    @Before
    public void setUp() throws Exception
    {
        em = emFactory.createEntityManager();
        userEJB.setEm(em);
        groupEJB.setEm(em);
    }

    @After
    public void tearDown() throws Exception
    {
        if (em != null)
            em.close();
    }

    @Test
    public void testLazy()
    {
        System.err.println("testLazy");
        User u = userEJB.findUserByLogin("test@example.com");
        Group g = groupEJB.findGroupByName("admins");
        logger.info(u.toString());
        logger.info("user " + u.getGroups());
        logger.info("group " + g.getUsers());
    }

    @Test
    public void testPersistence()
    {
        System.err.println("testPersistence");
        try
        {
            User u = new User();
            u.setFirstName("Marek");
            u.setLastName("Balla");
            u.setPassword("long-password");
            u.setLogin("test@example.com");

            Group g = new Group();
            g.setName("admins");

            em.getTransaction().begin();
            userEJB.createUser(u);
            groupEJB.createGroup(g);
            u.getGroups().add(g);
            g.getUsers().add(u);
            em.getTransaction().commit();

        } catch (Exception ex)
        {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
