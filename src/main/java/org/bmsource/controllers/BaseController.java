package org.bmsource.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class BaseController
{
    public String redirect(String page)
    {
        return page + "?faces-redirect=true";
        //        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        //        try
        //        {
        //            context.redirect(context.getRequestContextPath() + page);
        //        } catch (IOException e)
        //        {
        //            e.printStackTrace();
        //        }

    }

    public String refresh()
    {
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true";
    }

    public void flashMessage(String id, String message)
    {
        errorMessage(id, message);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public void errorMessage(String id, String message)
    {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, msg);
    }
}
