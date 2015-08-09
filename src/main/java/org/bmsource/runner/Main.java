package org.bmsource.runner;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.WebServiceRef;

import org.bmsource.beans.BookEJBRemote;
import org.bmsource.model.Book;
import org.bmsource.ws.client.CardValidatorService;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ws();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @WebServiceRef
    private static CardValidatorService cardValidatorService;

    private static void ws() throws Exception
    {
        org.bmsource.ws.client.CreditCard creditCard = new org.bmsource.ws.client.CreditCard();
        creditCard.setNumber("12341234");
        creditCard.setExpiryDate("10/12");
        creditCard.setType("VISA");
        creditCard.setControlNumber(1234);
        System.out.println(cardValidatorService.getCardValidatorPort().validate(creditCard));
    }

    private static void jms() throws Exception
    {
        try
        {
            Context jndiContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/javaee7/ConnectionFactory");
            Destination queue = (Destination) jndiContext.lookup("jms/javaee7/Queue");

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);

            // Sends a text message to the queue
            TextMessage message = session.createTextMessage("Text message sent at " + new Date());
            producer.send(message);
            connection.close();
        } catch (NamingException | JMSException e)
        {
            e.printStackTrace();
        }
    }

    private static void jaxb() throws NamingException, JAXBException
    {
        Context ctx = new InitialContext();
        BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/blog/BookEJB!org.bmsource.beans.BookEJBRemote");
        List<Book> books = bookEJB.findBooks();

        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller m = context.createMarshaller();
        StringWriter writer = new StringWriter();
        for (Book aBook : books)
        {
            m.marshal(aBook, writer);
            System.err.println(writer);
        }
    }

    private static void lookupJndi() throws NamingException
    {
        Context ctx = new InitialContext();
        BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/blog/BookEJB!org.bmsource.beans.BookEJBRemote");

        // Gets and displays all the books from the database
        List<Book> books = bookEJB.findBooks();
        for (Book aBook : books)
            System.out.println(aBook);

        Book book = new Book("H2G2", "Scifi book", 12.5F, "1-24561-799-0");
        book = bookEJB.createBook(book);
        System.out.println("Book created : " + book);
        book.setTitle("H2G2");
        book = bookEJB.updateBook(book);
        System.out.println("Book updated : " + book);
        bookEJB.deleteBook(book);
        System.out.println("Book deleted");
    }

    //    private static void embeddedTest()
    //    {
    //        Weld weld = new Weld();
    //        WeldContainer container = weld.initialize();
    //        BookService bookService = container.instance().select(BookService.class).get();
    //        Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
    //        System.out.println(book);
    //
    //        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
    //        EntityManager em = emf.createEntityManager();
    //
    //        Customer c = new Customer();
    //        c.setFirstName("Marek");
    //        c.setLastName("Balla");
    //        c.setDateOfBirth(new Date(System.currentTimeMillis() - 2000000));
    //        c.setEmail("marecica2@gmail.com");
    //
    //        EntityTransaction tx = em.getTransaction();
    //        tx.begin();
    //        em.persist(c);
    //        tx.commit();
    //
    //        weld.shutdown();
    //    }
}
