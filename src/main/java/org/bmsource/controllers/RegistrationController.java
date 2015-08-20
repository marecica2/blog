package org.bmsource.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.UserEJB;
import org.bmsource.model.User;
import org.bmsource.model.Utils;

@Named
@RequestScoped
public class RegistrationController extends BaseController
{
    @Inject
    private UserEJB userEJB;

    private final User user = new User();

    public void register()
    {
        user.setPassword(Utils.encodeSHA256(user.getPassword()));
        userEJB.createUser(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                        "The user" + user.getLogin() + " has been created with id=" + user.getId()));
        redirect("index.xhtml");
    }

    public User getUser()
    {
        return user;
    }

}
