package org.bmsource.controllers;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

	Map<String, String> params = getParams();

	public Map<String, String> getParams() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	public void postRedirect() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String page = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getRequestURI();
		try {
			context.redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String redirect(String page) {
		return page + "?faces-redirect=true";
		// ExternalContext context =
		// FacesContext.getCurrentInstance().getExternalContext();
		// try
		// {
		// context.redirect(context.getRequestContextPath() + page);
		// } catch (IOException e)
		// {
		// e.printStackTrace();
		// }

	}

	public String refresh() {
		UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		return view.getViewId() + "?faces-redirect=true";
	}

	public void flashMessage(String id, String message) {
		errorMessage(id, message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void errorMessage(String id, String message) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(id, msg);
	}
}
