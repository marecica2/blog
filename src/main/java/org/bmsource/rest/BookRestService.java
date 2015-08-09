package org.bmsource.rest;

import java.net.URI;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.bmsource.model.Book;
import org.bmsource.model.Books;

@Path("/book")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Stateless
public class BookRestService
{
    @PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @POST
    public Response createBook(Book book)
    {
        if (book == null)
            throw new BadRequestException();
        em.persist(book);
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        return Response.created(bookUri).build();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") String id)
    {
        Book book = em.find(Book.class, Long.parseLong(id));
        if (book == null)
            throw new NotFoundException();
        return Response.ok(book).build();
    }

    @GET
    public Response getBooks()
    {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        Books books = new Books(query.getResultList());
        return Response.ok(books).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") String id)
    {
        Book book = em.find(Book.class, id);
        if (book == null)
            throw new NotFoundException();
        em.remove(book);
        return Response.noContent().build();
    }
}
