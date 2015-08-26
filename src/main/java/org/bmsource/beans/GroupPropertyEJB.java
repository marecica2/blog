package org.bmsource.beans;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.bmsource.model.GroupProperty;

@Named
@Stateless
@LocalBean
public class GroupPropertyEJB extends BaseEJB<GroupProperty, Long> implements Serializable
{
    @Inject
    private EntityManager em;

    private static final long serialVersionUID = -1193177975841547046L;

    public GroupPropertyEJB()
    {
        super(GroupProperty.class);
    }

    @Override
    public EntityManager getEm()
    {
        return this.em;
    }

    @Override
    public void setEm(EntityManager em)
    {
        this.em = em;
    }

}
