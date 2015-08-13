package org.bmsource.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bmsource.beans.UserEJB;
import org.bmsource.model.User;

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

    public String login()
    {
        User user = userEJB.findUserByLogin(login);
        if (user == null)
        {
            System.err.println("User does not exist");
            errorMessage("loginForm:login", "User does not exist");
        }
        if (user != null && !user.getPassword().equals(password))
        {
            System.err.println("Incorrect password");
            errorMessage("loginForm:password", "Incorrect password");
        }
        if (user != null && user.getPassword().equals(password))
        {
            userController.setUser(user);
            return redirect("/index.xhtml");
        }
        return "login.xhtml";
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
