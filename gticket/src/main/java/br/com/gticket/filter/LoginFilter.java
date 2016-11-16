package br.com.gticket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gticket.util.FacesUtil;

@WebFilter(servletNames = "Faces Servlet")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if ((session.getAttribute("usuarioLogado") != null)
				|| (req.getRequestURI().endsWith("index.xhtml"))
				|| (req.getRequestURI().endsWith("form_usuario.xhtml"))
				|| (req.getRequestURI().contains("javax.faces.resource/"))) {

			filterChain.doFilter(request, response);

		} else {

			if (req.getRequestURI().contains("/logged/")) {
				redirect("../index.xhtml?erro=true", response);
			} else {
				redirect("index.xhtml?erro=true", response);
			}

		}

	}

	private void redirect(String url, ServletResponse response)
			throws IOException {

		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
