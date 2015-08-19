package org.bmsource.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.bmsource.model.Group;

@Named
@Stateless
@LocalBean
public class GroupEJB implements Serializable {

	private static final long serialVersionUID = -1193177975841547046L;

	@Inject
	private EntityManager em;

	public List<Group> findGroups() {
		TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g", Group.class);
		return query.getResultList();
	}

	public @NotNull Group createGroup(@NotNull Group group) {
		em.persist(group);
		return group;
	}

	public @NotNull Group updateGroup(@NotNull Group group) {
		return em.merge(group);
	}

	public void deleteGroup(@NotNull Group group) {
		em.remove(em.merge(group));
	}

	public Group findGroupById(@NotNull Long id) {
		return em.find(Group.class, id);
	}

	public Group findGroupByName(String name) {
		try {
			return em.createQuery("from Group g where g.name = :name", Group.class).setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
