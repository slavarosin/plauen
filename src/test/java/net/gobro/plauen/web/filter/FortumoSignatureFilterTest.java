package net.gobro.plauen.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.web.filter.FortumoSignatureFilter;

import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.MockFilterChain;
import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

public class FortumoSignatureFilterTest {

	private FortumoSignatureFilter filter;
	private MockFilterChain filterChain;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		filterChain = new MockFilterChain();

		filter = new FortumoSignatureFilter();
		filter.setSignatureParameterName("sig");
		filter.setServiceIdParameterName("serviceId");

		Map<String, String> signatures = new HashMap<String, String>();
		signatures.put("22463760dca366bc034545fb0fade345",
				"28b01b849508aa28447efcb89f8eb62b");
		signatures.put("0417f77382729da6475b7b446e47987c",
				"9cd3165314071d02b34271a950586585");
		signatures.put("d03896c87871097ed471b389102d2d6d",
				"9ee3d847da46c55428a081db4c3c9f2b");
		signatures.put("a189a21eab22534fafd5850df6aaec00",
				"d55ab373282159d99cb53b27ff23a9dc");
		signatures.put("44d48401a352ff338995c0b155316082",
				"6fff9c4b3db74fb0546ed70b0ca650fe");
		signatures.put("03e4df9268a921860a537ddad4fbf25d",
				"38c68525545be2c0b179dc3a9a6af281");
		signatures.put("3b849aa49de662ae8308ebfcd99db0dd",
				"2ef9b71eefda88ad450de0e411cdce58");
		signatures.put("6c5f72aa0962b32024922b53c9a7c4b3",
				"f30fb3aef6df3936fcd0baf07b13e0eb");
		signatures.put("ce0ea6f65eb4dd3f5dbf80b28e99f45a",
				"89fd9c46a8b39d2929d9d459127dc4d1");

		filter.setSignatures(signatures);
	}

	@Test
	public void testCheckInvalidSignature() throws IOException,
			ServletException {
		List<String> parameterNames = new ArrayList<String>();
		parameterNames.add("sig");
		parameterNames.add("serviceId");
		request.setupGetParameterNames(Collections.enumeration(parameterNames));

		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", "invalidSignature");
		// "sig" will be consumed two times
		request.setupAddParameter("sig", "invalidSignature");
		request.setupAddParameter("serviceId",
				"22463760dca366bc034545fb0fade345");
		// "serviceId" will be consumed two times
		request.setupAddParameter("serviceId",
				"22463760dca366bc034545fb0fade345");
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testCheckNoParameters() throws IOException, ServletException {
		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", (String) null);
		request.setupAddParameter("serviceId", (String) null);
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testCheckNoServiceId() throws IOException, ServletException {
		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", "someSignature");
		request.setupAddParameter("serviceId", (String) null);
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testCheckNoSignature() throws IOException, ServletException {
		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", (String) null);
		request.setupAddParameter("serviceId", "someID");
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testCheckServiceIsNotRegistered() throws IOException,
			ServletException {
		List<String> parameterNames = new ArrayList<String>();
		parameterNames.add("sig");
		parameterNames.add("serviceId");
		request.setupGetParameterNames(Collections.enumeration(parameterNames));

		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", "28b01b849508aa28447efcb89f8eb62b");
		// "sig" will be consumed two times
		request.setupAddParameter("sig", "28b01b849508aa28447efcb89f8eb62b");

		request.setupAddParameter("serviceId", "unknownServiceId");
		// "serviceId" will be consumed two times
		request.setupAddParameter("serviceId", "unknownServiceId");
		response.setExpectedError(HttpServletResponse.SC_FORBIDDEN);

		filter.doFilter(request, response, filterChain);

		response.verify();
	}

	@Test
	public void testCheckValidSignature() throws IOException, ServletException {
		List<String> parameterNames = new ArrayList<String>();
		parameterNames.add("sig");
		parameterNames.add("serviceId");
		request.setupGetParameterNames(Collections.enumeration(parameterNames));

		request.setupGetRemoteAddr("127.0.0.1");
		request.setupAddParameter("sig", "4e3d0ce686f5fa7fb11862940a0cd5af");
		// "sig" will be consumed two times
		request.setupAddParameter("sig", "4e3d0ce686f5fa7fb11862940a0cd5af");

		request.setupAddParameter("serviceId",
				"22463760dca366bc034545fb0fade345");
		// "serviceId" will be consumed two times
		request.setupAddParameter("serviceId",
				"22463760dca366bc034545fb0fade345");
		response.setExpectedErrorNothing();

		filter.doFilter(request, response, filterChain);

		response.verify();
	}
}
