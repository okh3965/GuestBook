package com.bit.servlet;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name="encoding", value="UTF-8")
        }
)
public class EncodingFilter implements Filter {
	FilterConfig config;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}
	
	
	@Override
	public void doFilter(ServletRequest req, 
			ServletResponse resp, 
			FilterChain chain)
			throws IOException, ServletException {

		req.setCharacterEncoding(config.getInitParameter("encoding"));
		chain.doFilter(req, resp);
		
	}
	
	@Override
	public void destroy() {	}
	
}
