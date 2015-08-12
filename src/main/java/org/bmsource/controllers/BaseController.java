package org.bmsource.controllers;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class BaseController
{
    public void redirect(String page)
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try
        {
            context.redirect(context.getRequestContextPath() + page);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
