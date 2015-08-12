package org.bmsource.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.UserEJB;
import org.bmsource.model.User;

@Named("loggedUser")
@SessionScoped
public class LoginController extends BaseController implements Serializable
{
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

    @Inject
    private UserEJB userEJB;

    private String login;
    private String password;

    private User user;

    public Boolean isLogged()
    {
        if (user != null)
            return true;
        return false;
    }

    public String login()
    {
        User user = userEJB.findUserByLogin(login);
        if (user == null)
        {
            System.err.println("User does not exist");
            return "login.xhtml";
        }
        if (!user.getPassword().equals(password))
        {
            System.err.println("Incorrect password");
            return "login.xhtml";
        }
        this.user = user;

        redirect("/index.html");
        return "/index.xhtml";
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
