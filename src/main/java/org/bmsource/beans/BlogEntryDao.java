package org.bmsource.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bmsource.model.BlogEntry;

@Stateless
public class BlogEntryDao
{
    @PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager em;

    public BlogEntry saveBlogEntry(BlogEntry blogEntry)
    {
        em.persist(blogEntry);
        return blogEntry;
    }

    public List<BlogEntry> findBlogEntries()
    {
        final TypedQuery<BlogEntry> query = em.createQuery("SELECT b FROM BlogEntry b ORDER BY b.created DESC", BlogEntry.class);
        List<BlogEntry> entries = query.getResultList();
        if (entries == null)
            entries = new ArrayList<BlogEntry>();
        return entries;
    }

    public void deleteBlogEntry(BlogEntry blogEntry)
    {
        blogEntry = em.merge(blogEntry);
        em.remove(blogEntry);
    }
}
