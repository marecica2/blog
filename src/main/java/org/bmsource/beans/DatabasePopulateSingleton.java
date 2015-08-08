//package org.bmsource.beans;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.sql.DataSourceDefinition;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.inject.Inject;
//
//import org.bmsource.model.Book;
//
//@Singleton
//@Startup
//@DataSourceDefinition(
//        className = "org.postgresql.Driver",
//        name = "jdbc:postgresql://localhost:6433/blog",
//        user = "postgres",
//        password = "postgres",
//        databaseName = "blog",
//        properties = { "connectionAttributes=;create=true" })
//public class DatabasePopulateSingleton
//{
//    @Inject
//    private BookEJB bookEJB;
//
//    private Book h2g2;
//    private Book lord;
//
//    @PostConstruct
//    private void populateDB()
//    {
//        h2g2 = new Book("Beginning Java EE 7", "Great book", 35F, "1-8763-9125-7");
//        lord = new Book("The Lord of the Rings", "SciFi ", 50.4f, "1-84023-742-2");
//        bookEJB.createBook(h2g2);
//        bookEJB.createBook(lord);
//    }
//
//    @PreDestroy
//    private void clearDB()
//    {
//        bookEJB.deleteBook(h2g2);
//        bookEJB.deleteBook(lord);
//    }
//}
