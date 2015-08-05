package org.bmsource.beans;

import javax.inject.Inject;

import org.bmsource.generator.NumberGenerator;
import org.bmsource.interceptor.Loggable;
import org.bmsource.model.Book;

@Loggable
public class BookService
{
    @Inject
    private NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description)
    {
        Book book = new Book();
        book.setDescription(description);
        book.setPrice(price);
        book.setTitle(title);
        book.setNumber(numberGenerator.generateNumber());
        return book;
    }
}
