package br.com.gticket.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static HttpSession getSession() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(
				false);
		return sessao;
	}

	public static void setParam(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getParam(String key) {
		return getSession().getAttribute(key);
	}

	public static void remove(String key) {
		getSession().removeAttribute(key);
	}

	public static void invalidate() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		getSession().invalidate();
		ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
	}

}
