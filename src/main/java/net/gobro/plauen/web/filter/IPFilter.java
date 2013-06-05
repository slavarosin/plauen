package net.gobro.plauen.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class IPFilter extends GenericFilterBean {
	private static final Logger LOG = LoggerFactory.getLogger(IPFilter.class);

	private List<String> remoteIPList;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String userIP = request.getRemoteAddr();
		HttpServletResponse httpResponse = null;

		if (response instanceof HttpServletResponse) {
			httpResponse = (HttpServletResponse) response;
		}

		if (!getRemoteIPList().contains(userIP)) {
			if (httpResponse != null) {
				if (LOG.isInfoEnabled()) {
					LOG
							.info("Forbid access to the restricted part of the application for remote user ["
									+ userIP
									+ "] by responding with status ["
									+ HttpServletResponse.SC_FORBIDDEN + "]");
				}

				httpResponse
						.sendError(HttpServletResponse.SC_FORBIDDEN,
								"You are not allowed to access this part of application.");
			} else {
				if (LOG.isInfoEnabled()) {
					LOG
							.info("Forbid access to the restricted part of the application for remote user ["
									+ userIP
									+ "] by responding with RuntimeException");
				}

				throw new RuntimeException(
						"You are not allowed to access this part of application.");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public List<String> getRemoteIPList() {
		return remoteIPList;
	}

	public void setRemoteIPList(List<String> remoteIPList) {
		this.remoteIPList = remoteIPList;
	}

	public void setRemoteIPs(String addresses) {
		setRemoteIPList(new ArrayList<String>());

		Scanner scanner = new Scanner(addresses);
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			String ip = StringUtils.trimToNull(scanner.next());

			if ((ip != null) && !getRemoteIPList().contains(ip)) {
				getRemoteIPList().add(ip);
			}
		}
	}
}
