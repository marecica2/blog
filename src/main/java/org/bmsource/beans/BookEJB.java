package org.bmsource.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.bmsource.model.Book;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote
{
    @Inject
    private EntityManager em;

    @Override
    public List<Book> findBooks()
    {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return query.getResultList();
    }

    @Override
    public @NotNull Book createBook(@NotNull Book book)
    {
        System.out.println("$$$$$ persisting book " + book);
        em.persist(book);
        return book;
    }

    @Override
    public @NotNull Book updateBook(@NotNull Book book)
    {
        return em.merge(book);
    }

    @Override
    public void deleteBook(@NotNull Book book)
    {
        System.out.println("$$$$$ deleting book " + book);
        em.remove(em.merge(book));
    }
}
