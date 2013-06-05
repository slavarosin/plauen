package net.gobro.plauen.web.filter;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

public class FortumoSignatureFilter extends GenericFilterBean implements
		InitializingBean {
	private static final Logger LOG = LoggerFactory.getLogger(IPFilter.class);

	private String signatureParameterName;
	private String serviceIdParameterName;
	private Map<String, String> signatures;

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		Assert.notNull(getSignatures(), "A signatures are required");
		Assert.notEmpty(getSignatures(), "A map with signatures is empty");
		Assert.notNull(getServiceIdParameterName(),
				"A name of request parameter with service ID is required");
		Assert.notNull(getSignatureParameterName(),
				"A name of request parameter with signature is required");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String userIP = request.getRemoteAddr();
		HttpServletResponse httpResponse = null;

		if (response instanceof HttpServletResponse) {
			httpResponse = (HttpServletResponse) response;
		}

		String serviceId = request.getParameter(getServiceIdParameterName());
		String signature = request.getParameter(getSignatureParameterName());

		if (signature == null || serviceId == null) {
			if (LOG.isWarnEnabled()) {
				LOG
						.warn("Forbid access for remote user ["
								+ userIP
								+ "] to the restricted part of the application because of "
								+ (signature == null ? "missed \""
										+ getSignatureParameterName()
										+ "\" parameter" : "missed \""
										+ getServiceIdParameterName()
										+ "\" parameter"));
			}

			sendError(userIP, httpResponse);
		} else {
			String ethalonSignature = getSignatures().get(serviceId);

			if (ethalonSignature == null) {
				if (LOG.isWarnEnabled()) {
					LOG
							.warn("Forbid access for remote user ["
									+ userIP
									+ "] to the restricted part of the application because the requested service ID ["
									+ serviceId
									+ "] is not registered in the application config");
				}

				sendError(userIP, httpResponse);
			}

			ethalonSignature = calculateSignature(request, ethalonSignature);

			if (ethalonSignature != null
					&& ethalonSignature.equals(request
							.getParameter(getSignatureParameterName()))) {
				chain.doFilter(request, response);
			} else {
				if (LOG.isWarnEnabled()) {
					LOG
							.warn("Forbid access for remote user ["
									+ userIP
									+ "] to the restricted part of the application because given signature ["
									+ signature
									+ "] does not equals the the signature ["
									+ ethalonSignature
									+ "] calclulated from parameters and security code defined in configuration file ["
									+ getSignatures().get(serviceId)
									+ "] for the service [" + serviceId + "]");
				}

				sendError(userIP, httpResponse);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private String calculateSignature(ServletRequest request,
			String ethalonSignature) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Forbid access because of internal error", e);
			return null;
		}

		List<String> keys = new ArrayList<String>(Collections.list(request
				.getParameterNames()));
		Collections.sort(keys);

		String s = "";
		for (String k : keys) {
			if (!k.equals(getSignatureParameterName())) {
				s += k + "=" + request.getParameter(k);
			}
		}

		s += ethalonSignature;

		byte[] messageDigest = digest.digest(s.getBytes());
		BigInteger bigint = new BigInteger(1, messageDigest);
		return bigint.toString(16);
	}

	private void sendError(String userIP, HttpServletResponse httpResponse)
			throws IOException {
		if (httpResponse != null) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Responding with status ["
						+ HttpServletResponse.SC_FORBIDDEN
						+ "] for remote user [" + userIP + "]");
			}

			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
					"You are not allowed to access this part of application.");
		} else {
			if (LOG.isInfoEnabled()) {
				LOG
						.info("Responding with with RuntimeException for remote user ["
								+ userIP + "]");
			}

			throw new RuntimeException(
					"You are not allowed to access this part of application.");
		}
	}

	public String getSignatureParameterName() {
		return signatureParameterName;
	}

	public void setSignatureParameterName(String parameterName) {
		this.signatureParameterName = parameterName;
	}

	public void setServiceIdParameterName(String serviceIdParameterName) {
		this.serviceIdParameterName = serviceIdParameterName;
	}

	public String getServiceIdParameterName() {
		return serviceIdParameterName;
	}

	public void setSignatures(Map<String, String> signatures) {
		this.signatures = signatures;
	}

	public Map<String, String> getSignatures() {
		return signatures;
	}
}
