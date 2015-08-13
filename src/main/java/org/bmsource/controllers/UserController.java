package org.bmsource.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.bmsource.model.User;

@Named("loggedUser")
@SessionScoped
public class UserController extends BaseController implements Serializable
{
    private User user;

    public Boolean isLogged()
    {
        if (user != null)
            return true;
        return false;
    }

    public User getUser()
    {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        System.err.println(sessionId);
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
