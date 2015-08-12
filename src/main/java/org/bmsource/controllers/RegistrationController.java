package org.bmsource.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.UserEJB;
import org.bmsource.model.User;

@Named
@RequestScoped
public class RegistrationController
{
    @Inject
    private UserEJB userEJB;

    @Inject
    private LoginController login;

    private final User user = new User();

    public String register()
    {
        userEJB.createUser(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                        "The user" + user.getLogin() + " has been created with id=" + user.getId()));
        return "newBook.xhtml";
    }

    public User getUser()
    {
        return user;
    }

}
