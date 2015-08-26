package org.bmsource.beans;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class BaseEJB<T, PK extends Serializable>
{
    protected Class<T> entityClass;

    public BaseEJB(Class<T> clazz)
    {
        this.entityClass = clazz;
    }

    public T save(T t)
    {
        getEm().persist(t);
        return t;
    }

    public T getById(PK id)
    {
        return getEm().find(entityClass, id);
    }

    public T update(T t)
    {
        return getEm().merge(t);
    }

    public void delete(T t)
    {
        t = getEm().merge(t);
        getEm().remove(t);
    }

    abstract public EntityManager getEm();

    abstract public void setEm(EntityManager em);
}
