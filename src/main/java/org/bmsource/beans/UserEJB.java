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

import org.bmsource.model.User;

@Named
@Stateless
@LocalBean
public class UserEJB implements Serializable {

	private static final long serialVersionUID = 7389005918246289966L;

	@Inject
	private EntityManager em;

	public List<User> findUsers(Boolean prefetch) {
		if (prefetch) {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.groups g", User.class);
			List<User> u = query.getResultList();
			return u;
		} else {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			return query.getResultList();
		}
	}

	public @NotNull User createUser(@NotNull User User) {
		em.persist(User);
		return User;
	}

	public @NotNull User updateUser(@NotNull User User) {
		return em.merge(User);
	}

	public void deleteUser(@NotNull User User) {
		em.remove(em.merge(User));
	}

	public User findUserById(@NotNull Long id) {
		return em.find(User.class, id);
	}

	public User findUserByLogin(String login) {
		try {
			return em.createQuery("from User u where u.login = :login", User.class).setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
