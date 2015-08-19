package org.bmsource.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.GroupEJB;
import org.bmsource.beans.UserEJB;
import org.bmsource.interceptor.Authentify;
import org.bmsource.model.Group;
import org.bmsource.model.User;

@Authentify
@Named
@javax.faces.view.ViewScoped
public class GroupController extends BaseController implements Serializable {

	private static final long serialVersionUID = 73918487008364154L;

	@Inject
	private GroupEJB groupEJB;

	@Inject
	private UserEJB userEJB;

	private List<Group> groups;

	private List<User> users;

	private Group group = new Group();

	private boolean edit;

	@PostConstruct
	public void init() {
		users = userEJB.findUsers(true);
		System.err.println(users);
		groups = groupEJB.findGroups();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void add() {
		groupEJB.createGroup(group);
		postRedirect();
	}

	public void edit(Group group) {
		this.group = group;
		System.err.println(group);
		edit = true;
	}

	public void save() {
		groupEJB.updateGroup(group);
		edit = false;
		group = new Group();
		postRedirect();
	}

	public void delete(Long id) {
		Group g = groupEJB.findGroupById(id);
		groupEJB.deleteGroup(g);
		postRedirect();
	}

	public boolean isEdit() {
		return edit;
	}

	public void addToGroup(User user) {
		group.getUsers().add(user);
		groupEJB.updateGroup(group);
		user.getGroups().add(group);
		userEJB.updateUser(user);
	}

	public void removeFromGroup(User user) {
		user.getGroups().remove(group);
		userEJB.updateUser(user);
		group.getUsers().remove(user);
		groupEJB.updateGroup(group);
	}

	public void removeUser(User user) {
		userEJB.deleteUser(user);
	}

}
