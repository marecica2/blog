package org.bmsource.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.bmsource.beans.UserEJB;
import org.bmsource.model.User;
import org.bmsource.model.Utils;

@Named
@RequestScoped
public class LoginController extends BaseController
{
    @Inject
    private UserEJB userEJB;

    @Inject
    private UserController userController;

    private String login;

    private String password;

    private String originalURL;

    @PostConstruct
    public void init()
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null)
        {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else
        {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null)
            {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login()
    {
        User user = userEJB.findUserByLogin(login);
        if (user == null)
            errorMessage("loginForm:login", "User does not exist");
        else
        {
            password = Utils.encodeSHA256(password);
            System.err.println(user.getPassword());
            System.err.println(password);
            if (!user.getPassword().equals(password))
                errorMessage("loginForm:password", "Incorrect password");
            else
            {
                try
                {
                    userController.setUser(user);
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    externalContext.redirect(originalURL);
                    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
                    System.err.println(login + " : " + password);
                    request.login(login, password);
                } catch (ServletException | IOException e)
                {
                    e.printStackTrace();
                }
                redirect("/index.xhtml");
            }
        }
    }

    public String logout()
    {
        userController.setUser(null);
        return redirect("/index.xhtml");
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
