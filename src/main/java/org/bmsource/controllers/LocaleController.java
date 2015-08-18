package org.bmsource.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable
{

    private Locale locale;

    @PostConstruct
    public void init()
    {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale()
    {
        return locale;
    }

    public String getLanguage()
    {
        return locale.getLanguage();
    }

    public void setLanguage(String language)
    {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}