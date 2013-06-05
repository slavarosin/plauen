package net.gobro.plauen.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.web.filter.IPFilter;

import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.MockFilterChain;
import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

public class IPFilterTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockFilterChain filterChain;
	private IPFilter filter;

	@Test
	public void testNotValidIP() throws IOException, ServletException {
		request.setupGetRemoteAddr("127.0.0.1");
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testValidIP() throws IOException, ServletException {
		request.setupGetRemoteAddr("81.20.151.38");
		response.setExpectedErrorNothing();

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		filterChain = new MockFilterChain();

		filter = new IPFilter();
		filter.setRemoteIPs("81.20.151.38,81.20.148.122");
	}
}
