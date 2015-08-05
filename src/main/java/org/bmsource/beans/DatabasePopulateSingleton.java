package org.bmsource.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.bmsource.model.Book;

@Singleton
@Startup
public class DatabasePopulateSingleton
{
    @Inject
    private BookEJB bookEJB;

    private Book h2g2;
    private Book lord;

    @PostConstruct
    private void populateDB()
    {
        System.out.println("####################");
        h2g2 = new Book("Beginning Java EE 7", "Great book", 35F, "1-8763-9125-7");
        lord = new Book("The Lord of the Rings", "SciFi ", 50.4f, "1-84023-742-2");

        bookEJB.createBook(h2g2);
        bookEJB.createBook(lord);
    }

    @PreDestroy
    private void clearDB()
    {
        bookEJB.deleteBook(h2g2);
        bookEJB.deleteBook(lord);
    }
}
