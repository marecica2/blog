//package org.bmsource.interceptor;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Map;
//
//import javax.el.ELContext;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.Interceptor;
//import javax.interceptor.InvocationContext;
//
//@Interceptor
//@Authentify
//public class AuthentifyInterceptor implements Serializable {
//
//	private String getArgName(int index) {
//		return "arg" + index;
//	}
//
//	private Authentify getSecureAnnotation(Method m) {
//		for (Annotation a : m.getAnnotations()) {
//			if (a instanceof Authentify) {
//				return (Authentify) a;
//			}
//		}
//		for (Annotation a : m.getDeclaringClass().getAnnotations()) {
//			if (a instanceof Authentify) {
//				return (Authentify) a;
//			}
//		}
//
//		throw new RuntimeException(
//				"@Secure not found on method " + m.getName() + " or its class " + m.getClass().getName());
//	}
//
//	@AroundInvoke
//	public Object authentifyMethod(InvocationContext ctx) throws Exception {
//		FacesContext facesCtx = FacesContext.getCurrentInstance();
//		ELContext elCtx = facesCtx.getELContext();
//
//		Authentify secure = getSecureAnnotation(ctx.getMethod());
//		String expression = "";
//
//		Map<String, Object> requestMap = facesCtx.getExternalContext().getRequestMap();
//		for (int i = 0; i < ctx.getParameters().length; i++) {
//			Object parameter = ctx.getParameters()[i];
//			requestMap.put(getArgName(i), parameter);
//		}
//
//		Boolean expressionValue = (Boolean) facesCtx.getApplication().getExpressionFactory()
//				.createValueExpression(elCtx, expression, Boolean.class).getValue(elCtx);
//
//		// Removing the parameters (arg0, arg1, ...)
//		for (int i = 0; i < ctx.getParameters().length; i++) {
//			requestMap.remove(getArgName(i));
//		}
//
//		if (expressionValue == null || !expressionValue) {
//			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//			try {
//				context.redirect("/login.xhtml");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return ctx.proceed();
//	}
//}
