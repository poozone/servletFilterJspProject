package com.advancedJava.filters;

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

import com.advancedJava.classes.DbProvider;
import com.advancedJava.classes.UserRepository;
import com.advancedJava.classes.items.User;


@WebFilter({ "/index.jsp", "/register.jsp" })
public class RegisterAndLoginFilter implements Filter {

    
	public void destroy() {}

	
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		

		
		if(session.getAttribute("logged") != null) {
			resp.sendRedirect("profile.jsp?profile="+((User)session.getAttribute("logged")).getUsername());
		}
		
		chain.doFilter(request, response);
	}

	
	
	public void init(FilterConfig fConfig) throws ServletException {
		
		// w tym miejscu tworze instancje bazy danych, za jej pomoca tworze repozytorium, ktore potem zapisuje w kontekscie aplikacji
		fConfig.getServletContext().setAttribute("userRepo", new UserRepository(DbProvider.getDataBase()));
	}

}
