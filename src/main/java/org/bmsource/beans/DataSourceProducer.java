package org.bmsource.beans;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DataSourceProducer
{
    @Produces
    @PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager em;
}
