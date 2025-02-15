package org.ssssssss.magicapi.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.ssssssss.magicapi.model.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MagicCorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void destroy() {

	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		String value = request.getHeader("Origin");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, StringUtils.isBlank(value) ? "*" : value);
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		value = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
		if (StringUtils.isNotBlank(value)) {
			response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, value);
		}
		value = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD);
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, StringUtils.isBlank(value) ? "GET,POST,OPTIONS,PUT,DELETE" : value);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (StringUtils.isNotBlank(Constants.HEADER_REQUEST_SESSION)) {
			process(request, (HttpServletResponse) resp);
		}
		chain.doFilter(req, resp);
	}
}
