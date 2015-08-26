package org.bmsource.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.GroupEJB;
import org.bmsource.beans.UserEJB;
import org.bmsource.model.Group;
import org.bmsource.model.User;

@Named
@RequestScoped
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
		System.err.println("init");
		groups = groupEJB.findGroups();
		edit = params.get("edit") != null ? true : false;
		if (edit) {
			group = params.get("group") != null ? groupEJB.findGroupById(Long.parseLong(params.get("group"))) : null;
			users = userEJB.findUsers(true);
		}
	}

	public void add() {
		groupEJB.createGroup(group);
		postRedirect();
	}

	public String save() {
		Group toUpdate = groupEJB.findGroupById(Long.parseLong(params.get("group")));
		toUpdate.setName(group.getName());
		groupEJB.updateGroup(toUpdate);
		return redirect("groups.xhtml");
	}

	public void delete(Long id) {
		Group g = groupEJB.findGroupById(id);
		System.err.println(g);
		System.err.println(g);
		System.err.println(g);
		groupEJB.deleteGroup(g);
		postRedirect();
	}

	public void addToGroup() {
		User user = userEJB.findUserById(Long.parseLong(params.get("user")));
		Group group = groupEJB.findGroupById(Long.parseLong(params.get("group")));
		group.getUsers().add(user);
		groupEJB.updateGroup(group);
		this.group = group;
		refresh();
	}

	public void removeFromGroup() {
		User user = userEJB.findUserById(Long.parseLong(params.get("user")));
		Group group = groupEJB.findGroupById(Long.parseLong(params.get("group")));
		user.getGroups().remove(group);
		userEJB.updateUser(user);
		group.getUsers().remove(user);
		this.group = group;
		refresh();
	}

	public void removeUser(User user) {
		System.err.println("remove user");
		userEJB.deleteUser(user);
	}

	//
	// getters
	//

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

	public boolean isEdit() {
		return edit;
	}

	public GroupEJB getGroupEJB() {
		return groupEJB;
	}

	public void setGroupEJB(GroupEJB groupEJB) {
		this.groupEJB = groupEJB;
	}

	public UserEJB getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserEJB userEJB) {
		this.userEJB = userEJB;
	}
}
