<%@ page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><fmt:setLocale
value="${countryLocale}" /><fmt:setBundle
basename="messages" /><fmt:message
key="${messageKey}">
	<fmt:param value="${sender}" />
	<fmt:param value="${message}" />
</fmt:message>