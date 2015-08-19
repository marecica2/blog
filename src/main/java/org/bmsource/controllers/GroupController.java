package org.bmsource.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.GroupEJB;
import org.bmsource.model.Group;

@Named
@javax.faces.view.ViewScoped
public class GroupController extends BaseController implements Serializable
{
    @Inject
    GroupEJB groupEJB;

    List<Group> groups;
    private Group group = new Group();
    private boolean edit;

    @PostConstruct
    public void init()
    {
        groups = groupEJB.findGroups();
    }

    public List<Group> getGroups()
    {
        return groups;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public void setEdit(boolean edit)
    {
        this.edit = edit;
    }

    public void add()
    {
        groupEJB.createGroup(group);
        postRedirect();
    }

    public void edit(Group group)
    {
        this.group = group;
        edit = true;
    }

    public void save()
    {
        groupEJB.updateGroup(group);
        edit = false;
        group = new Group();
        postRedirect();
    }

    public void delete(Long id)
    {
        Group g = groupEJB.findGroupById(id);
        groupEJB.deleteGroup(g);
        postRedirect();
    }

    public boolean isEdit()
    {
        return edit;
    }
}
