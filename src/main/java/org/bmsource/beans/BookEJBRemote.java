package org.bmsource.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;

import org.bmsource.model.Book;

@Remote
public interface BookEJBRemote
{
    public List<Book> findBooks();

    public @NotNull Book createBook(@NotNull Book book);

    public @NotNull Book updateBook(@NotNull Book book);

    public Book findBookById(@NotNull Long id);

    public void deleteBook(@NotNull Book book);
}