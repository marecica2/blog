package org.bmsource.runner;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.bmsource.beans.BookService;
import org.bmsource.model.Book;
import org.bmsource.model.Customer;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main
{
    public static void main(String[] args)
    {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        BookService bookService = container.instance().select(BookService.class).get();
        Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
        System.out.println(book);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        Customer c = new Customer();
        c.setFirstName("Marek");
        c.setLastName("Balla");
        c.setDateOfBirth(new Date(System.currentTimeMillis() - 2000000));
        c.setEmail("marecica2@gmail.com");

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(c);
        tx.commit();

        weld.shutdown();

    }
}
