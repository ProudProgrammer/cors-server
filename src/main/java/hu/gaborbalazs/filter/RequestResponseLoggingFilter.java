package hu.gaborbalazs.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseLoggingFilter implements Filter {

	@Autowired
	private Logger logger;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		StringBuilder stringBuilder = new StringBuilder(System.lineSeparator());
		stringBuilder.append("Request HTTP Method: ");
		stringBuilder.append(req.getMethod());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Request URL: ");
		stringBuilder.append(req.getRequestURL());
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Request Headers:");
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append(Collections.list(req.getHeaderNames()).stream()
				.map(headerName -> "> " + headerName + "=" + req.getHeader(headerName))
				.collect(Collectors.joining(System.lineSeparator())));
		logger.info(stringBuilder.toString());

		chain.doFilter(request, response);

		logger.info("Response Status: " + res.getStatus() + System.lineSeparator() + "Response Headers"
				+ System.lineSeparator()
				+ res.getHeaderNames().stream().map(headerName -> "> " + headerName + "=" + res.getHeader(headerName))
						.collect(Collectors.joining(System.lineSeparator())));
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
