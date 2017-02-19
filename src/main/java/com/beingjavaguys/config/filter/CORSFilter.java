package com.beingjavaguys.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Expose-Headers", "x-requested-with");
		
		/* response.addHeader("Access-Control-Allow-Origin", "*");
         response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
         response.addHeader("Access-Control-Allow-Credentials","true");
         response.addHeader("Access-Control-Allow-Headers",request.getHeader("Access-Control-Request-Headers"));*/
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
}