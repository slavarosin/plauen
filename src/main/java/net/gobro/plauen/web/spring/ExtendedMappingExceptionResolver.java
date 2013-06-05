package net.gobro.plauen.web.spring;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExtendedMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	private Log errorLogger;

	private List<String> ignoreExceptions;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (ex.getCause() != null && ex.getCause() instanceof SocketException) {
			return null;
		} else if (getIgnoreExceptions().contains(ex.getClass().getName())) {
			logException(ex, request);

			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, ex
						.getMessage());
			} catch (IOException e) {
				logException(e, request);
			}

			return null;
		} else {
			return super.doResolveException(request, response, handler, ex);
		}
	}

	public List<String> getIgnoreExceptions() {
		return ignoreExceptions;
	}

	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		if ((this.errorLogger != null) && this.errorLogger.isErrorEnabled()) {
			this.errorLogger.error(buildLogMessage(ex, request), ex);
		} else {
			super.logException(ex, request);
		}
	}

	public void setErrorLogCategory(String loggerName) {
		this.errorLogger = LogFactory.getLog(loggerName);
	}

	public void setIgnoreExceptions(List<String> ignoreExceptions) {
		this.ignoreExceptions = ignoreExceptions;
	}
}
