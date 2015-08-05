package org.bmsource.generator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.bmsource.model.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookTest
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inMemory");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception
    {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception
    {
        if (em != null)
            em.close();
    }

    @Test
    public void shouldFindBook() throws Exception
    {
        Book book = em.find(Book.class, 1001L);
        Assert.assertEquals("Beginning Java EE 7", book.getTitle());
        System.err.println(book.getId());
    }

    @Test
    public void shouldCreateBook() throws Exception
    {
        Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F, "1-84023-742-2");
        tx.begin();
        em.persist(book);
        tx.commit();
        Assert.assertNotNull("ID should not be null", book.getId());
        book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        Assert.assertEquals("The Hitchhiker's Guide to the Galaxy", book.getDescription());
        System.err.println(book.getId());
    }

    //    @Test(expected = ConstraintViolationException.class)
    //    public void shouldRaiseConstraintViolationCauseNullTitle()
    //    {
    //        Book book = new Book(null, "Null title, should fail", 12.5F, "1-84023-742-2");
    //        em.persist(book);
    //    }
}
