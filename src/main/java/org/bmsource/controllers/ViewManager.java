package org.bmsource.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bmsource.model.Property;

@ManagedBean(name = "viewManager")
@ViewScoped
public class ViewManager implements Serializable
{

    ArrayList<Property> cacheList = new ArrayList<>();
    private Property item = new Property();

    public ViewManager()
    {
    }

    private boolean edit;

    public void add()
    {
        cacheList.add(item);
        item = new Property();

    }

    public void edit(Property item)
    {
        this.item = item;
        edit = true;
    }

    public void save()
    {
        item = new Property();
        edit = false;
    }

    public void delete(Property item)
    {
        cacheList.remove(item);
    }

    public List<Property> getCacheList()
    {
        return cacheList;
    }

    public Property getItem()
    {
        return item;
    }

    public boolean isEdit()
    {
        return edit;
    }

    private boolean item1 = false;
    private String item2 = null;
    private final Date date = new Date();
    private Integer item3 = null;

    public Integer getItem3()
    {
        return item3;
    }

    public void setItem3(Integer item3)
    {
        this.item3 = item3;
    }

    public Date getDate()
    {
        return date;
    }

    public String getItem2()
    {
        return item2;
    }

    public void setItem2(String item2)
    {
        this.item2 = item2;
    }

    public boolean getItem1()
    {
        return item1;
    }

    public void setItem1(boolean item1)
    {
        this.item1 = item1;
    }
}
