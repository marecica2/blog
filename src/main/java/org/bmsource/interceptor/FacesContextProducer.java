package org.bmsource.interceptor;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

public class FacesContextProducer {
	@Produces
	public FacesContext produceLogger(InjectionPoint injectionPoint) {
		return FacesContext.getCurrentInstance();
	}
}