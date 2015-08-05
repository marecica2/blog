package org.bmsource.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.model.BlogEntry;

@Named("blogEntryBean")
@RequestScoped
public class BlogEntryBean
{
    @Inject
    private BlogEntryDao blogEntryDao;

    private BlogEntry blogEntry = new BlogEntry();

    public List<BlogEntry> getBlogEntries()
    {
        return blogEntryDao.findBlogEntries();
    }

    public BlogEntry getBlogEntry()
    {
        System.err.println("get");
        return blogEntry;
    }

    public void setBlogEntry(BlogEntry blogEntry)
    {
        this.blogEntry = blogEntry;
    }

    public String saveBlogEntry()
    {
        System.err.println("saving");
        blogEntryDao.saveBlogEntry(blogEntry);
        return "success";
    }

    public void delete(BlogEntry blogEntry)
    {
        System.err.println("deleting");
        blogEntryDao.deleteBlogEntry(blogEntry);
    }
}