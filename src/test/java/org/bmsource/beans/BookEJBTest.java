package org.bmsource.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.bmsource.model.Book;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookEJBTest
{
    private static Context ctx;
    private static EJBContainer ejbContainer;

    @BeforeClass
    public static void setUp()
    {
        System.out.println("Opening the container");
        System.out.println(new File("target/classes").getAbsolutePath());
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ejbContainer = EJBContainer.createEJBContainer(properties);
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    public static void tearDown()
    {
        ejbContainer.close();
        System.out.println("Closing the container");
    }

    @Test
    public void shouldCreateABook() throws Exception
    {
        // Looks up the EJB
        BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/blog/BookEJB!org.bmsource.beans.BookEJB");

        // Finds all the books and makes sure there are two (inserted by the DBPopulator)
        assertEquals(2, bookEJB.findBooks().size());

        // Creates an instance of book
        Book book = new Book("H2G2", "Scifi book", 12.5F, "1-24561-799-0");

        // Persists the book to the database
        book = bookEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Finds all the books and makes sure there is an extra one
        assertEquals(3, bookEJB.findBooks().size());

        // Removes the created book
        bookEJB.deleteBook(book);

        // Finds all the books and makes sure there is one less
        assertEquals(2, bookEJB.findBooks().size());
    }
}
