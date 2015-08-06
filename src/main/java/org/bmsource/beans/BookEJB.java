package org.bmsource.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.bmsource.model.Book;

@Named
@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote
{
    @Inject
    private EntityManager em;

    @Override
    public List<Book> findBooks()
    {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public @NotNull Book createBook(@NotNull Book book)
    {
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
        em.remove(em.merge(book));
    }

    @Override
    public Book findBookById(@NotNull Long id)
    {
        return em.find(Book.class, id);
    }
}
